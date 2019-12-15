// Adrián Navarro Gabino

const mongoose = require('mongoose');
const express = require('express');
const bodyParser = require('body-parser');
const methodOverride = require('method-override')

mongoose.Promise = global.Promise;
mongoose.connect('mongodb://localhost:27017/contacts');

let contactSchema = new mongoose.Schema({
    name: {
        type: String,
        required: true,
        minlength: 1,
        trim: true
    },
    telephone: {
        type: String,
        required: true,
        trim: true,
        match: /^\d{9}$/
    },
    age: {
        type: Number,
        min: 18,
        max: 120
    }
});

let Contact = mongoose.model('contact', contactSchema);

let app = express();
app.use(bodyParser.urlencoded({
    extended: true
}));
app.use(bodyParser.json());
app.use(methodOverride('_method'));

app.get('/',function(req,res){
    res.sendfile("index.html");
});

app.get('/contacts', (req, res) => {
    Contact.find().then(result => {
        res.send(result);
    })
});

app.get('/contacts/:id', (req, res) => {
    Contact.findById(req.params.id).then(result => {
        if (result) {
            let data = {error: false, result: result};
            res.send(data);
        } else {
            let data = {error: true,
            errorMessage: "Contact not found"};
            res.send(data);
        }
    }).catch(error => {
        let data = {error: true,
        errorMessage: "Error getting contact"};
        res.send(data);
    });
});

app.post('/contacts', (req, res) => {
    let newContact = new Contact({
        name: req.body.name,
        telephone: req.body.telephone,
        age: req.body.age
    });
    newContact.save().then(result => {
        let data = {error: false, result: result};
        res.send(data);
    }).catch(e => {
        let data = {error: true,
        errorMessage:"Error adding contact: " + e};
        res.send(data);
    });
});

app.put('/contacts/:id', (req, res) => {
    res.send(req.body);
    Contact.findByIdAndUpdate(req.params.id, {
            $set: {
            name: req.body.name,
            telephone: req.body.telephone,
            age: req.body.age
        }
    }, {new: true}).then(result => {
        let data = {error: false, result: result};
        res.send(data);
    }).catch(e => {
        let data = {error: true,
        errorMessage:"Error updating contact: " + e};
        res.send(data);
    });
});

app.delete('/contacts/:id', (req, res) => {
    Contact.findByIdAndRemove(req.params.id).then(result => {
        let data = {error: false, result: result};
        res.send(data);
    }).catch(error => {
        let data = {error: true,
        errorMessage:"Error removing contact"};
        res.send(data);
    });
});

app.listen(8080);