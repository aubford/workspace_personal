var exp = require('express')
var app = exp()

var knex = require('knex')({
  client:'pg',
  connection: 'postgres://localhost/vehicles'
})

function Cars() {
  return knex('cars')
}


app.get('/',function(req,res){
  Cars().select().then(function(result){
    var content = result
    res.send(content)
  })
})

app.listen('4000', function(){
  console.log('startng server on port 4000')
})
