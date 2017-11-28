var app = angular.module('ingApp', ['ngRoute'])

		app.config(function($routeProvider) {
			$routeProvider
				.when('/', {
					templateUrl: '../partials/index.html',
					controller: 'MainController'
				})
				.otherwise({redirectTo : '/'})
		})
