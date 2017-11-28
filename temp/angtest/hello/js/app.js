var app = angular.module('helloApp', ['ngRoute'])

		app.config(function($routeProvider) {
			$routeProvider
				.when('/', {
					templateUrl: '../partials/index.html',
					controller: 'MainController'
				})
				.otherwise({redirectTo : '/'})
		})
