// Adrián Navarro Gabino

const mongoose = require('mongoose');

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

let commentSchema = new mongoose.Schema(
    {
        date: {
            type: Date,
            required: true,
            default: Date.now
        },
        nick: {
            type: String,
            required: true
        },
        comment: {
            type: String,
            required: true
        }
    }
)

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
        },
        comments: [commentSchema]
    }
);

let Book = mongoose.model("book", bookSchema);

if(process.argv[2] == 'i')
{
    let author = new Author({
        firstName: "Patrick",
        lastName: "Rothfuss",
        yearOfBirth: 1973
    });

    author.save().then(a => {
        let book = new Book({
            title: "The name of the wind",
            author: a._id,
            price: 20
        });

        book.comments.push({nick: "anonymous", comment: "Good book"});
        book.comments.push({
            date: Date.parse("11/11/2010"),
            nick: "George21",
            comment: "Not bad"
        });

        book.save().then(b => console.log(b));

        book = new Book({
            title: "The wise man's fear",
            author: a._id,
            price: 23
        });

        book.comments.push({
            nick: "alfred19",
            comment: "It's better than the first one"
        });

        book.save().then(b => console.log(b));
    });

    author = new Author({
        firstName: "Terry",
        lastName: "Pratchett",
        yearOfBirth: 1948
    });

    author.save().then(a => {
        let book = new Book({
            title: "The colour of magic",
            author: a._id,
            price: 10
        });

        book.save().then(b => console.log(b));
    });
}
else if(process.argv[2] == 'l')
{
    Book.find().populate('author').then(b => console.log(b));
}
else if(process.argv[2] == 'u')
{
    Author.findOneAndUpdate({firstName: "Terry", lastName: "Pratchett"},
        {$set: {yearOfBirth: 2000}}, {new: true}).then(b => console.log(b));
}