var express = require('express');
var router = express.Router();
var pg = require('pg')

var knex = require('knex')({
  client:'pg',
  connection: 'postgres://localhost/phishsongs'
})

router.get('/', function(req, res, next) {
  knex('gamehenge').select().then(function(results){
      res.render('index', {songs : results})
  })
});

module.exports = router;
