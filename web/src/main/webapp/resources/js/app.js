'use strict';
// Declare app level module which depends on filters, and services
var expenses = angular.module('badam7', [ 'ngRoute', 'homeController']);

expenses.config([ '$routeProvider', function($routeProvider) {
    $routeProvider.when('/home', {
        templateUrl : 'resources/html/home.html',
        controller : 'homeController'
    });

    $routeProvider.otherwise({
        redirectTo : '/'
    });
} ]);
