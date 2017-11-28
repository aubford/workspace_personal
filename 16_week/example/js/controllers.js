app.controller('MainController', ['$scope', 'factest', function($scope, factest){
      $scope.routes='HTML and Routes'
      $scope.working='Working'
      factest($scope, 'factest')
    }])
