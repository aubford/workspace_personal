app.factory('factest', ['$http',function($http){
  return function(scope, name){
    $http.get('http://www.omdbapi.com/?i=tt0076759').then(function(results){
        return scope[name] = results.data.Title
    })
  }
}])
