var express = require("express")

var app = express()


//view engine
app.set("view engine", "ejs")
// app.set("view engine","jade")
//if you want to use Jade



//ROUTES

app.get("/", function(req /*request coming in */, res /*response going out*/){
  var individual = "mike"
  res.render("index", {"individual" : individual})
  //looks in folder called views for an .ejs file
})

// app.get("/people", function(req,res){
//   res.send("hello to everyone")
// })

app.get("/people/:name/:city/:state", function(req,res){
  var individual = req.params.name;
  var city = req.params.city
  var state = req.params.state

  // res.send("hello to everyone, especially "+individual+"who lives in "+state)

  res.render("people", {"state":state, "individual" : individual, "city" : city})
})

app.get("/profiles/:dinosaur", function(req,res){
  var dinosaur = req.params.dinosaur;
  res.render("profile", {"dinosaur": dinosaur})
})

app.get("*", function(req,res){
  res.send("404 not found")
})



app.listen(3000,function(){
  console.log("I am listenening on port 3000.  Recognize me.")
})
