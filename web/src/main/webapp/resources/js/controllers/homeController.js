var homeController = angular.module('homeController', [ 'ngRoute' ]).controller('homeController',['$scope', '$route', '$location', 'Game', 'Player', function($scope, $route, $location, Game, Player) {
                    'use strict';

        $scope.addgame = false;
        $scope.newgame = {};

        Game.query().$promise.then(function(games) {
            $scope.games = games;            
        });  

        Player.get(1, 1, 1).$promise.then(function(cards) {
            $scope.cards = cards;  

        });  

        $scope.joinGame = function (gameId){
            console.log(gameId) 
            Player.save(1, gameId).$promise.then(function(result) {
                $location.path('/game');
            }); 
        }

        $scope.startCreateGame = function (){
            $scope.addgame = true;
        }

        $scope.createGame = function (gameId){
            $scope.newgame.gameType = 1;
            Game.save($scope.newgame).$promise.then(function(result) {
                $scope.newgame = {};
                
                $scope.addgame = false;
            });  
        }
    }
]);