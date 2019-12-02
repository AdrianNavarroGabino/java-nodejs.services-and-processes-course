// Adrián Navarro Gabino

const path = 'C:/Users/Adrian';
const fs = require('fs');
fs.readdirSync(path).forEach(file => {console.log(file);});