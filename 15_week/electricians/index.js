var app = angular.module("aubrey", [])

app.controller("MyController", function($scope){

  $scope.mirror = "Sleeping Beauty"
  $scope.contractors = []
  $scope.show = false
  $scope.msg = "Add a contractor"


  $scope.getContractor =  function(){
    // if ($scope.show === false){
    //   $scope.show = true
    // } else {
    //   $scope.show = false
    $scope.show = !$scope.show


    if ($scope.contractors.length) {
      $scope.msg = "Add another contractor"
    }

    $scope.contractors.push({name : $scope.name, rate: $scope.rate})
    $scope.name = null
    $scope.rate = null
    console.log($scope.contractors);
  }


})

app.controller("SecondGuy", function($scope){
  $scope.mirror = "Armanita"
})
