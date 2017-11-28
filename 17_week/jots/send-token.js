var jwt = require('jsonwebtoken')

var payload = {message:"you're a dickbag"}
var secret = 'galvanize_rocks!'
var options = {}

jwt.sign(payload, secret, options, getToken)

function getToken(token){
  console.log(token);
}
