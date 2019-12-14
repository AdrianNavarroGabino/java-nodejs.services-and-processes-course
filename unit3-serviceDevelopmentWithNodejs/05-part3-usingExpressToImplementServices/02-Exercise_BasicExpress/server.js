// Adrián Navarro Gabino

const express = require('express');
const moment = require('moment');
const now = new moment();
const os = require('os');
const name = os.userInfo().username;

let app = express();

app.get('/date', (req, res) => {
    res.send("Date: " + now.format('DD/MM/YYYY'));
});

app.get('/user', (req, res) => {
    res.send("User: " + name);
});

app.listen(8080);