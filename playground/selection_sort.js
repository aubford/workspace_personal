
var array = [3,2,7,1,1,5,2,3,4]
// [ ,3,7,2]

function selectionSort(arr){
  var temp

  for (var j = 0 ; j < arr.length - 1 ; j++){
    temp = arr[j]

    for (var i = j+1 ; i < arr.length ; i++){
      if (temp > arr[i]){
        var tempHolder = temp
        temp = arr[i]
        arr[i]=tempHolder
      }
    }
    arr[j]=temp
  }
  return arr
}

console.log(selectionSort(array))
