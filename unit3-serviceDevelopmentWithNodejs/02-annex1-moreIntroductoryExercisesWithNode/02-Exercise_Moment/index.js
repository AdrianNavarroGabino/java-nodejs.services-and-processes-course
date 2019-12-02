// Adrián Navarro Gabino

const moment = require('moment');

const now = new moment();

let date1String = "07/03/2013";
let date1 = new moment(date1String, "DD/MM/YYYY");

if(date1.isValid())
{
    console.log(date1.format('DD/MM/YYYY') + " is valid");
}
else
{
    console.log("The date is not valid");
}

let date2String = "07/13/2013";
let date2 = new moment(date2String, "DD/MM/YYYY");

if(date2.isValid())
{
    console.log(date2.format('DD/MM/YYYY') + " is valid");
}
else
{
    console.log("The date is not valid");
}

let date3String = "19/01/2019";
let date3 = new moment(date3String, "DD/MM/YYYY");

console.log(date3.diff(date1, 'days') + " days");
console.log(date3.diff(date1, 'years') + " years");

let minutesLater = now.add(8, 'minutes');
console.log(minutesLater.fromNow());