var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  // res.cookie("name", "peterson")
  // res.clearCookie("name")
  res.cookie("views", (parseInt(req.cookies.views)||0)+1)
  res.render('index', {views:req.cookies.views});
});

router.get('/reset',function(req,res){
  res.clearCookie("views")
  res.redirect('/')
})

module.exports = router;
