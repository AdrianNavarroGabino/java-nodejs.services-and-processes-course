// Adrián Navarro Gabino

const mongoose = require('mongoose');

mongoose.Promise = global.Promise;
mongoose.connect('mongodb://localhost:27017/contacts');

let addressSchema = new mongoose.Schema({
    street: {
        type: String,
        required: true,
        minlength: 1,
        trim: true
    },
    number: {
        type: Number,
        required: true,
        min: 1
    },
    city: {
        type: String,
        required: true,
        minlength: 1,
        trim: true
    },
    postalCode: {
        type: Number,
        minlength: 1
    }
});

let Address = mongoose.model('addresses', addressSchema);

let contactSchema = new mongoose.Schema({
    name:
    {
        type: String,
        required: true,
        minlength: 1,
        trim: true
    },
    telephone: 
    {
        type: String,
        required: true,
        trim: true,
        match:/^\d{9}$/
    },
    age:
    {
        type: Number,
        min: 18,
        max: 120
    },
    address: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'addresses',
        required: true
    }
});

let Contact = mongoose.model('contact', contactSchema);

if(process.argv[2] == 'i')
{
    let address = new Address({street: "C/Lillo Juan", number: 128,
        city: "San Vicente", postalCode: 3690});

    address.save().then(a => {
        let contact = new Contact({name: "Antonio", telephone: "966666669",
            age: 25, address: a._id});

        contact.save().then(c => console.log(c))
                .catch(e => console.log("Error adding contact:", e));
    }).catch(err => console.log("Error adding address:", err));
}
else if(process.argv[2] == 'l')
{
    Contact.find().populate('address').then(c => console.log(c));
}