function Stack(){
  this.data = [1,5,4,3]
}

Stack.prototype.push = function(input){
  this.data.push(input)
}

Stack.prototype.pop = function(){
  this.data.pop()
}

Stack.prototype.top = function(){
  return this.data[this.data.length-1]
}

Stack.prototype.isEmpty = function(){
  return this.data.length === 0
}

Stack.prototype.getSize = function(){
  return this.data.length
}

var stack = new Stack()

console.log(stack.getSize());
console.log(stack.isEmpty());
