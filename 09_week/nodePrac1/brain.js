var calc = require("./calculator");
var total = calc(10.00);
console.log(total);
// returns original amount with tip applied.


var albums = require("./albums");
var first_album = albums[5];
console.log(first_album)
//returns the sixth album in an array


var backpack  = require("./backpack");
var name = backpack.book.isbn;
console.log(name);
// returns the isbn, which is a value within the book object within the backpack object

var cat = require("./cat");
cat() ;
// console logs “meow”
