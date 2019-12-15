// Adrián Navarro Gabino

const mongoose = require('mongoose');
const express = require('express');

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

app.listen(8080);