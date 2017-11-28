function Stack(){
  this.data = [1,5,4,3]
}

Stack.prototype.enqueue = function(input){
  this.data.push(input)
}

Stack.prototype.dequeue = function(){
  this.data.shift()
}

Stack.prototype.front = function(){
  return this.data[0]
}

Stack.prototype.isEmpty = function(){
  return this.data.length === 0
}

Stack.prototype.getSize = function(){
  return this.data.length
}

var stack = new Stack()

console.log(stack.dequeue());
console.log(stack.front());
