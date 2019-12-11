// Adrián Navarro Gabino

const mongoose = require('mongoose');

mongoose.Promise = global.Promise;
mongoose.connect('mongodb://localhost:27017/contacts');

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
    }
});

let Contact = mongoose.model('contacts', contactSchema);

if(process.argv[2] == 'i')
{
    let contact1 = new Contact({
        name: "David",
        telephone: "966666666",
        age: 20
    });
    
    contact1.save()
            .then(result =>{
                console.log("Contact added:", result);
            })
            .catch(error =>{
                console.log("ERROR adding contact:", error);
            });

    contact1 = new Contact({
        name: "Emilio",
        telephone: "966666667",
        age: 50
    });
        
    contact1.save()
            .then(result =>{
                console.log("Contact added:", result);
            })
            .catch(error =>{
                console.log("ERROR adding contact:", error);
            });

    contact1 = new Contact({
        name: "Enrique",
        telephone: "966666668",
        age: 30
    });
            
    contact1.save()
            .then(result =>{
                console.log("Contact added:", result);
            })
            .catch(error =>{
                console.log("ERROR adding contact:", error);
            });
}
else if(process.argv[2] == 'l')
{
    Contact.find().then(c => console.log(c));
    Contact.find({name: "Enrique", telephone: "966666668"})
            .then(c => console.log(c));
}
else if(process.argv[2] == 'u')
{
    Contact.findOneAndUpdate({name: "Emilio"}, {$set: {age: 20}},
        {new: true}).then(c => console.log(c));
}
else if(process.argv[2] == 'd')
{
    Contact.findOneAndDelete({name: "David"}).then(c => {
        if(c != null)
            console.log(c);
        else
            console.log("Contact not found");
    });
}