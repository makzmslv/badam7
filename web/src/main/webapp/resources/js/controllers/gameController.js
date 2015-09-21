var gameController = angular.module('gameController', [ 'ngRoute' ]).controller('gameController',['$scope', '$route', '$location', 'Game', 'Player', function($scope, $route, $location, Game, Player) {
                    'use strict';

        $scope.addgame = false;
        $scope.newgame = {};

    
        Player.get(1, 1, 1).$promise.then(function(cards) {
            $scope.cards = cards;  

        });  

        
    }
]);