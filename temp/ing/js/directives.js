app.directive('templateT', function(){
  return {
    scope: {
      workingy: '='
    },
    controller: ['$scope', function($scope){
      console.log('Directive Controller ' + $scope.workingy)
      $scope.test = 'hello'
    }],
    templateUrl: '../partials/template.html',
    transclude: true
    }
})

app.directive('link', function(){
  return {
    link: function(scope, element, attrs) {
      element.click(function(){
        console.log('Link ' + scope.workingy)
      })
    }
  }
})
