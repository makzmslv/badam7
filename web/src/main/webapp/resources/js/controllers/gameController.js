var gameController = angular.module('gameController', [ 'ngRoute' ]).controller('gameController',['$scope', '$rootScope', '$interval', 'Game', 'Player', function($scope, $rootScope, $interval, Game, Player) {
                    'use strict';

        var baseImgSrc = "resources/img/cards/";  
    
        
        var initPage = function () 
        {
            $scope.startButton = false;
            $scope.play = false;
        }
        
        $scope.reload = function ()
        {
             checkGameStatus();
             getPlayerCards();  
             getHandCards();
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
                  if(game.currentPlayerId === $rootScope.playerId)
                  {
                      $scope.playButton = true;
                  }
                  else
                  {
                      $scope.playButton = false;
                  }
              });  
        }
        
        var getPlayerCards = function () 
        {
            if($scope.play) 
            {
                var pid = $scope.game.playerIds[$rootScope.playerId];
                Player.get($rootScope.playerId, $scope.game.currentHandId, pid).$promise.then(function(cards) {
                    $scope.playerCards = cards;
                    angular.forEach($scope.playerCards, function (card) {
                        card.imgsrc = baseImgSrc + card.cardId + ".png"
                    });
                });  
            }
        }
        
        $scope.$on('$locationChangeStart', function( event ) {
            var answer = confirm("Are you sure you want to leave this page?")
            if (!answer) {
                event.preventDefault();
            }
        });
        
        var getHandCards = function () 
        {
            if($scope.play) 
            {
                Game.getCards($scope.game.currentHandId).$promise.then(function(cards) {
                    $scope.handCards = [cards[1], cards[2], cards[3], cards[4]];
                    $scope.heartCards = cards[1];
                    $scope.spadeCards = cards[2];
                    $scope.diamondCards = cards[3];
                    $scope.clubCards = cards[4];
                    angular.forEach($scope.heartCards, function (card) {
                        card.imgsrc = baseImgSrc + card.cardId + ".png"
                    });
                    angular.forEach($scope.diamondCards, function (card) {
                        card.imgsrc = baseImgSrc + card.cardId + ".png"
                    });
                    angular.forEach($scope.spadeCards, function (card) {
                        card.imgsrc = baseImgSrc + card.cardId + ".png"
                    });
                    angular.forEach($scope.clubCards, function (card) {
                        card.imgsrc = baseImgSrc + card.cardId + ".png"
                    });
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
        
        $scope.playCard = function () 
        {
           console.log("card played")
            if($scope.selectedCard !== undefined) 
            {
                Player.update($rootScope.playerId, $scope.selectedCard, false).$promise.then(function(card) {
                }); 
            }      
        }
        
        $scope.skipChance = function () 
        {
            Player.update($rootScope.playerId, $rootScope.gameId, true).$promise.then(function(game) {
                
            }, function(error) {
                alert("Cards available to play")
            }); 
        }
        
        $scope.selectCard =function (cardId)
        {
            console.log(cardId)
            $scope.selectedCard = cardId;
        }
        
        initPage();
        var stop = $interval(function() {
                        
                        checkGameStatus();
                        getPlayerCards();  
                        getHandCards();
                      }, 2000, 20);
        
    }
]);