// Adrián Navarro Gabino

const express = require('express');

let app = express();

app.get('/test', (req, res) => {
    res.send('Hello from test URI');
});

app.listen(8080);