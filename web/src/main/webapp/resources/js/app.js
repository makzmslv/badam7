'use strict';
// Declare app level module which depends on filters, and services
var expenses = angular.module('badam7', [ 'ngRoute', 'badam7Services', 'homeController', 'gameController']);

expenses.config([ '$routeProvider', function($routeProvider) {
    $routeProvider.when('/home', {
        templateUrl : 'resources/html/home.html',
        controller : 'homeController'
    }).when('/hand', {
        templateUrl : 'resources/html/game.html',
        controller : 'gameController'
    });;
    
    

    $routeProvider.otherwise({
        redirectTo : '/'
    });
} ]);
