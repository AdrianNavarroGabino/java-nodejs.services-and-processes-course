// Adrián Navarro Gabino

const mongoose = require('mongoose');
const express = require('express');
const bodyParser = require('body-parser');
const methodOverride = require('method-override');

mongoose.Promise = global.Promise;
mongoose.connect('mongodb://localhost:27017/books');

let authorSchema = new mongoose.Schema(
    {
        firstName: {
            type: String,
            required: true,
            minlength: 1,
            trim: true
        },
        lastName: {
            type: String,
            required: true,
            minlength: 1,
            trim: true
        },
        yearOfBirth: {
            type: Number,
            min: 1900,
            max: 2000
        }
    }
);

let Author = mongoose.model('author', authorSchema);

let bookSchema = new mongoose.Schema(
    {
        title: {
            type: String,
            required: true,
            minlength: 1,
            trim: true
        },
        author: {
            type: mongoose.Schema.Types.ObjectId,
            ref: 'author',
            required: true
        },
        price: {
            type: Number,
            min: 0
        }
    }
);

let Book = mongoose.model("book", bookSchema);

let app = express();
app.use(bodyParser.urlencoded({
    extended: true
}));
app.use(bodyParser.json());
app.use(methodOverride('_method'));

app.get('/',function(req,res){
    res.sendfile("index.html");
});

app.get('/books', (req, res) => {
    Book.find().populate('author').then(result => {
        res.send(result);
    })
});

app.get('/books/:id', (req, res) => {
    Book.findById(req.params.id).populate('author').then(result => {
        if (result) {
            let data = {error: false, result: result};
            res.send(data);
        } else {
            let data = {error: true,
            errorMessage: "Book not found"};
            res.send(data);
        }
    }).catch(e => {
        let data = {error: true,
        errorMessage: "Error getting book: " + e};
        res.send(data);
    });
});

app.post('/books', (req, res) => {
    let newBook = new Book({
        title: req.body.title,
        author: req.body.authorId,
        price: req.body.price
    });
    newBook.save().then(result => {
        let data = {error: false, result: result};
        res.send(data);
    }).catch(e => {
        let data = {error: true,
        errorMessage:"Error adding book: " + e};
        res.send(data);
    });
});

app.put('/books/:id', (req, res) => {
    res.send(req.body);
    Book.findByIdAndUpdate(req.params.id, {
            $set: {
            title: req.body.title,
            author: req.body.authorId,
            price: req.body.price
        }
    }, {new: true}).then(result => {
        let data = {error: false, result: result};
        res.send(data);
    }).catch(e => {
        let data = {error: true,
        errorMessage:"Error updating book: " + e};
        res.send(data);
    });
});

app.delete('/books/:id', (req, res) => {
    Book.findByIdAndRemove(req.params.id).then(result => {
        let data = {error: false, result: result};
        res.send(data);
    }).catch(e => {
        let data = {error: true,
        errorMessage:"Error removing book: " + e};
        res.send(data);
    });
});

app.get('/authors', (req, res) => {
    Author.find().then(result => {
        res.send(result);
    })
});

app.post('/authors', (req, res) => {
    let newAuthor = new Author({
        firstName: req.body.firstName,
        lastName: req.body.lastName,
        yearOfBirth: req.body.yearOfBirth
    });
    newAuthor.save().then(result => {
        let data = {error: false, result: result};
        res.send(data);
    }).catch(e => {
        let data = {error: true,
        errorMessage:"Error adding author: " + e};
        res.send(data);
    });
});

app.listen(8080);