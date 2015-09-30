'use strict';
// Declare app level module which depends on filters, and services
var expenses = angular.module('badam7', [ 'ui.bootstrap', 'ngRoute', 'badam7Services', 'homeController', 'gameController', 'loginController']);

expenses.config([ '$routeProvider', '$locationProvider', function($routeProvider, $locationProvider) {
    $routeProvider.when('/home/:playerId', {
        templateUrl : 'resources/html/home.html',
        controller : 'homeController'
    }).when('/home/:playerId/game/:gameId', {
        templateUrl : 'resources/html/game.html',
        controller : 'gameController'
    }).when('/login', {
        templateUrl : 'resources/html/login.html',
        controller : 'loginController'
    });
    $routeProvider.otherwise({
        redirectTo : '/login'
    });
    
} ]);
