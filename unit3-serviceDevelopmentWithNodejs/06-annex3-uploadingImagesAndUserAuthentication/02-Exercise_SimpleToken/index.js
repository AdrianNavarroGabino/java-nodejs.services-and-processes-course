// Adrián Navarro Gabino

const express = require('express');
const mongoose = require('mongoose');
const jwt = require('jsonwebtoken');
const bodyParser = require('body-parser');

const secretWord = "DAMsecret";

let generateToken = login => {
    let token = jwt.sign({login: login}, secretWord,
    {expiresIn:"30 minutes"});
    return token;
}

let validateToken = token => {
    try {
        let result = jwt.verify(token, secretWord);
        return result;
    } catch (e) {}
}

mongoose.Promise = global.Promise;
mongoose.connect('mongodb://localhost:27017/users');

let userSchema = new mongoose.Schema({
    login: {
        type: String,        
        required: true,        
        minlength: 1,        
        unique: true    
    },    
    password: {        
        type: String,        
        required: true,        
        minlength: 4    
    },    
    name: {        
        type: String,        
        required: true,        
        minlength: 1    
}});

let User = mongoose.model('user', userSchema);

let app = express();
app.use(bodyParser.json());

app.get('/users', (req, res) => {
    let token = req.headers['authorization'];
    let error = true;
    if (token) {
        if (validateToken(token)) {

            newToken = generateToken(token);
            error = false;
            User.find().then(data => {
                let result = {error: false, result: data, token: newToken};
                res.send(result);
            }).catch(error => {
                let result = {
                    error: true, 
                    errorMessage: "Error getting user list", 
                    token: newToken
                };
                res.send(result);                
            })

        } else {
            let result = {error: true, errorMessage: "Error validating user"};
            res.send(result);                
        }
    } 
    
    if (error) {
        let result = {error: true, errorMessage: "User is not authenticated"};
        res.send(result);        
    }
});

app.post('/users', (req, res) => {
    let newUser = new User({
        login: req.body.login,
        password: req.body.password,
        name: req.body.name
    });
    newUser.save().then(result => {
        let data = {error: false, result: result};
        res.send(data);
    }).catch(e => {
        let data = {error: true,
        errorMessage:"Error adding user: " + e, token: newToken};
        res.send(data);
    });
});

app.post('/login', (req, res) => {
    let userClient = {
        login: req.body.login,
        password: req.body.password
    };
        
    User.find({login: userClient.login,
        password: userClient.password})
    .then(data => {
        if (data) {
            let token = generateToken(userClient.login);
            let result = {error: false, token: token};
            res.send(result);
        } else {
            let result = {error: true,
            errorMessage: "Invalid user"};
            res.send(result);
        }
    }).catch (e => {
        let result = {error: true,
        errorMessage: "Error validating user: " + e};
        res.send(result);
    });
});