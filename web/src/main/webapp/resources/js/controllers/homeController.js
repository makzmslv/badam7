var homeController = angular.module('homeController', [ 'ngRoute' ]).controller('homeController',['$scope', '$route', '$rootScope', '$location', 'Game', 'Player', function($scope, $route, $rootScope, $location, Game, Player) {
                    'use strict';

        $scope.addgame = false;
        $scope.newgame = {};
        
        Game.query().$promise.then(function(games) {
            $scope.games = games;            
        });  

        $scope.joinGame = function (gameId){
            $rootScope.gameId = gameId;
            $rootScope.playGame = true;
              
            Player.save($rootScope.playerId, gameId).$promise.then(function(result) {
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