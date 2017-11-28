var express = require("express")

var app = express()

app.get("/", function(req /*request coming in */, res /*response going out*/){
  res.send("hello world")
})


app.listen(3000,function(){
  console.log("I am listenening on port 3000.  Recognize me.")
}
