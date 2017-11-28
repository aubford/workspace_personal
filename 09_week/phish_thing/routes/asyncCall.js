var express = require('express');
var router = express.Router();
var pg = require('pg')
/* GET home page. */
var connectionString = 'postgres://localhost/vehicles';

function runQuery (query, callback) {
  pg.connect(connectionString, function (err, client, done) {
    if (err) { done() ; console.log(err); return; }
    client.query(query, function (err, results) {
      done();
      if (err) { console.log(err); return; }
      callback(results);
    });
   });
}

router.get('/', function(req, res){
  runQuery('SELECT * FROM cars', function(results) {
     res.send(results.rows);
   });
});

router.get('/cars/:id', function(req, res){
  var thing = runQuery('SELECT * FROM cars WHERE id='+req.params.id, function(results) {
    var thang = runQuery('SELECT * FROM cars WHERE id='+6, function(results2) {
       var donenow= results2.rows[0]["make"]+" "+results2.rows[0]["model"] +"<br>"+
       results.rows[0]["make"]+" "+results.rows[0]["model"];
       res.send(donenow);
     });
   });
});

router.get('/cars/', function(req, res){
  //runQuery('SELECT * FROM cars WHERE id='+req.params.id, function(results) {
  runQuery('SELECT * FROM cars', function(results) {
     res.send(results);
   });
});

module.exports = router;
