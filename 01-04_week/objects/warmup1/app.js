


var profiles = [

    { name: "John", age:'22', city: "Denver"},
    { name: "Joe", age:'28', city: "Miami"},
    { name: "Allen", age:'324', city: "Omaha"}

]


var fruits = {
  citrus: ["lemon", "orange", "lime"],
  sweet: ["raspberry", "watermelon", "strawberry"],
  exotic: ["starfruit", "papaya", "guava"]
}
//
// for (var i = 0; i<profiles.length; i++) {
//     var local_obj = profiles[i]
//     for (p in local_obj) {
//       document.writeln(local_obj[p])
//     }
//     document.writeln('<br>')
// }



profiles.forEach(function(prof) {

  for (key in prof) {
    document.writeln(prof[key])
  }

})
