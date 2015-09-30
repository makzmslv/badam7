var homeController = angular.module('homeController', [ 'ngRoute' ]).controller('homeController',['$scope', '$route', '$rootScope', '$location', '$routeParams', 'Game', 'Player', function($scope, $route, $rootScope, $location, $routeParams, Game, Player) {
                    'use strict';

        $scope.addgame = false;
        $scope.newgame = {};
        
        var getGames = function()
        {
            Game.query().$promise.then(function(games) {
                $scope.games = games;            
            }); 
        }

        $scope.joinGame = function (gameId){
            $rootScope.gameId = gameId;
            var pid = $routeParams.playerId;
            Player.save(pid, gameId).$promise.then(function(result) {
                $location.path('/home/' + pid + '/game/' + gameId);
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
                getGames();
            });  
        } 
        
        getGames();
    }
                                                                                                  
]);