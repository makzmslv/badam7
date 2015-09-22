var gameController = angular.module('gameController', [ 'ngRoute' ]).controller('gameController',['$scope', '$rootScope', '$interval', 'Game', 'Player', function($scope, $rootScope, $interval, Game, Player) {
                    'use strict';

        $scope.startButton = false;
        $scope.play = false;
        $scope.pcgi = 0;
    
    
        
        var initPage = function () 
        {
            console.log($scope.play)
            checkGameStatus();
            getPlayerCards();            
        }
    
        var checkGameStatus = function () 
        {
              Game.get(1).$promise.then(function(game) {
                    $scope.game = game;
                  if(game.gameStatus === 3)
                  {
                      $scope.startButton = true;
                  }
                  if(game.gameStatus === 4)
                  {
                      $scope.play = true;
                      $scope.startButton = false;
                  }
              });  
        }
        
        var getPlayerCards = function () 
        {
            if($scope.play) 
            {
                Player.get($rootScope.playerId, $scope.game.currentHandId, $scope.pcgi).$promise.then(function(cards) {
                    $scope.cards = cards;  
                    console.log(cards[0])
                });  
            }
        }
                
        $scope.startGame = function ()
        {
            Game.update($rootScope.gameId).$promise.then(function(game) {
                $scope.game = game;  
                $scope.pcgi = game.playerIds[$rootScope.playerId];
                
            }, function(error) {
                console.log(error)
            }); 
        }   
        var stop = $interval(function() {
                console.log($scope.play)
                checkGameStatus();
                getPlayerCards();         
              }, 3000);
       
    }
]);