#!/bin/bash

ng ()
{
		if ! [[ -n $1 ]]; then
				echo "Enter a name after 'ng'";
				return 1;
		fi;
		mkdir $1;
		cd $1;
		mkdir css;
		mkdir js;
		mkdir partials;

		echo '<!doctype html>
		<html lang="en" ng-app="'$1'App">
			<head><meta charset="UTF-8">
				<title>'$1'</title>
				<link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet" media="screen">
				<link href="./css/main.css" rel="stylesheet" media="screen">
			</head>
			<body>

			<div ng-view></div>


			<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
			<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
			<script src="http://cdnjs.cloudflare.com/ajax/libs/angular.js/1.4.5/angular.js" type="text/javascript"></script>
			<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.3/angular-route.js"></script>
			<script src="./js/app.js" type="text/javascript"></script>
			<script src="./js/controllers.js" type="text/javascript"></script>
			<script src="./js/directives.js" type="text/javascript"></script>
			<script src="./js/filters.js" type="text/javascript"></script>
			<script src="./js/services.js" type="text/javascript"></script>
			</body>
		</html>' > index.html;


		echo "var app = angular.module('"${1}"App', ['ngRoute'])

		app.config(function(\$routeProvider) {
			\$routeProvider
				.when('/', {
					templateUrl: '../partials/index.html',
					controller: 'IndexController'
				})
				.otherwise({redirectTo : '/'})
		})" > js/app.js;

		echo "app.controller('IndexController', ['\$scope', function(\$scope){
				\$scope.test='HTML and Routes Working'
		}])" > js/controllers.js;

		echo "<p>{{test}}</p>" > partials/index.html

		touch js/directives.js;
		touch js/filters.js;
		touch js/services.js;
		touch css/main.css;
		ls;
		atom .;

		command -v http-server
		if [[ $? -eq 0 ]];
		then
			http-server -o -c-1
		fi
}


	if [[ -e ~/.bash_profile ]]
	then
		declare -f ng >> ~/.bash_profile
	elif [[ -e ~/.profile ]]
	then
		declare -f ng >> ~/.profile
	else
		echo "No bash initialization file found. Ignore if you don't use bash."
	fi

	if [[ -e ~/.zshrc ]]
	then
		declare -f ng >> ~/.zshrc
	fi

	exit 1
