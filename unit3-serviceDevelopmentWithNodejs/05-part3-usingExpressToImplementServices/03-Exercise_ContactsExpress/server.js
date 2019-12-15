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
app.listen(8080);