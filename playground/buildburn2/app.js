ban = "johnny"
allnames = ["jeff", "dan", "elroy", "johnny", "johnny", "john"]

var newnames = allnames.filter(function(name){
  return name !== "johnny"
})

console.log(newnames)
