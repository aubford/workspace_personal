var input = "https://www.myawesomething.com/index.html?full_name=Therese+Blogoyavich&age=44&location=San+Francisco"

output={}
key = []
value = []
keyTank = []
valueTank = []


pushKey = false
pushValue = false

// input = input.split("")

for (var i = 0; i < input.length; i++) {

  if (input[i-1]==="?" || input[i-1]==="&"){
    pushKey=true
  }else if (input[i]==="="){
    newKey = key.join("")
    keyTank.push(newKey)
    key = []
    pushKey=false
  }


  if (input[i-1]==="="){
    pushValue=true
  }else if (input[i]==="&"){
    pushValue=false
    newValue = value.join("")
    valueTank.push(newValue)
    value = []
  }else if (i===input.length-1){
    value.push(input[i])
    pushValue=false
    newValue = value.join("")
    valueTank.push(newValue)
    value = []
  }


  if (pushKey===true){
    key.push(input[i])
  }

  if (pushValue===true){
    value.push(input[i])
  }

}

console.log(keyTank)
console.log(valueTank)


for (var i = 0; i < keyTank.length; i++) {
  output[keyTank[i]] = valueTank[i]
}


console.log(output)


// Martha's Code:
//
// var input = "https://www.myawesomething.com/index.html?full_name=Therese+Blogoyavich&age=44&location=San+Francisco"
//
// function queryParser(query) {
//   query = query.split("?")[1];
//   query = query.split("&");
//   var results = {};
//   query.forEach(function (pairs) {
//     pairs = pairs.split("=");
//     var key = pairs[0];
//     var value = pairs[1].replace("+", " ");
//     results[key] = value;
//   })
//   return results;
// }
//
//
// console.log(queryParser(input));



//Chris' code:

// var input = "https://www.myawesomething.com/index.html?full_name=Therese+Blogoyavich&age=44&location=San+Francisco"
// ​
// function getQueryString(i){
//   return i.split('?')[1]
// }
// ​
// function getQueryObjectFromString(queryString){
//   return (queryString.split('&').reduce(function(acc, val){
//     var key = val.split('=')[0]
//     var value = val.split('=')[1].split('+').join(' ')
// ​
//     acc[key] = value
//     return acc
//   },{}))
// }
// ​
// function queryParser(input) {
//   return getQueryObjectFromString(getQueryString(input))
// }
// ​
// console.log(queryParser(input));
