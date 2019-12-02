// Adrián Navarro Gabino

const lodash = require('lodash');

let people = [
    {firstName: "Nacho", lastName: "Iborra", age: 39},    
    {firstName: "Juan", lastName:"Perez", age: 55},    
    {firstName: "Mario", lastName:"Calle", age: 4},    
    {firstName: "Ana", lastName:"Jiménez", age: 33},    
    {firstName: "Yeray", lastName:"Perez", age: 2},    
    {firstName: "Josefa", lastName:"Blasco", age: 48}
];

let firstNames = lodash.map(people, p => p.firstName);

console.log(firstNames);

let olderThan18 = lodash.orderBy(people, ['age'], ['desc']);
olderThan18 = lodash.takeWhile(olderThan18, p => p.age > 18);
olderThan18 = lodash.map(olderThan18, p => p.firstName);

console.log(olderThan18);

let averageAges = lodash.map(people, p => p.age);
averageAges = lodash.sum(averageAges) / people.length;

console.log(averageAges);