'use strict';
// Declare app level module which depends on filters, and services
var expenses = angular.module('badam7', [ 'ngRoute', 'badam7Services', 'homeController', 'gameController', 'loginController']);

expenses.config([ '$routeProvider', function($routeProvider) {
    $routeProvider.when('/home', {
        templateUrl : 'resources/html/login.html',
        controller : 'loginController'
    }).when('/hand', {
        templateUrl : 'resources/html/game.html',
        controller : 'gameController'
    }).when('/login', {
        templateUrl : 'resources/html/login.html',
        controller : 'loginController'
    });
    
    

    $routeProvider.otherwise({
        redirectTo : '/'
    });
} ]);
