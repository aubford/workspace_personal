var fs = require("fs")

fs.readFile('home.txt', 'utf-8', function(err, data){
  console.log(data)
})

fs.writeFile('helloworld.txt', 'My message to you!', function (err) {
  if (err) return console.log(err);
});

fs.appendFile('home.txt', "hehehehe")
