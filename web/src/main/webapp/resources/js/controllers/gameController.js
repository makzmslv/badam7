var gameController = angular.module('gameController', [ 'ngRoute' ]).controller('gameController',['$scope', '$rootScope', '$interval', '$routeParams', 'Game', 'Player', function($scope, $rootScope, $interval, $routeParams,  Game, Player) {
                    'use strict';

        var baseImgSrc = "resources/img/cards/";  
        var currentPlayer = 0;
        var playerId = playerId = parseInt($routeParams.playerId);
        var gameId = parseInt($routeParams.gameId);
    
        
        var initPage = function () 
        {
            $scope.startButton = false;
            $scope.play = false;
            $scope.showButton = false;
        }
        
        $scope.reload = function ()
        {
             checkGameStatus();
             getPlayerCards();  
             getHandCards();
        }
    
        var checkGameStatus = function () 
        {
            Game.get(gameId).$promise.then(function(game) {
                
                $scope.game = game;
                if(game.gameStatus === 3)
                {
                    $scope.startButton = true;
                }
                if(game.gameStatus === 4)
                {
                  if($scope.heartCards === undefined)
                  {
                      currentPlayer = game.currentPlayerId;
                      getPlayerCards();
                      getHandCards();
                  }
                  $scope.play = true;
                  $scope.startButton = false;
                }
                 if(game.gameStatus === 6)
                 {
                      $scope.showResults = true;
                      $scope.getResults();
                      $scope.play = false;
                 } 
                 if(game.currentPlayerId === playerId)
                 {
                      $scope.showButton = true;
                 }
                 else
                 {
                      $scope.showButton = false;
                 }
                 if(game.currentHandStatus === 2)
                 {
                      alert("Hand has ended.")
                      if(game.handWinnerId === playerId)
                      {
                          alert("The winner is player : " + game.handWinnerId)
                      }
                 }

                 if(currentPlayer !== game.currentPlayerId)
                 {
                      currentPlayer = game.currentPlayerId;
                      getPlayerCards();
                      getHandCards();
                 }
            });  
        }
        
        var getPlayerCards = function () 
        {
            if($scope.play) 
            {
                var playerGameId = $scope.game.playerIds[playerId];
                Player.get(playerId, $scope.game.currentHandId, playerGameId).$promise.then(function(cards) {
                    $scope.playerCards = cards;
                    angular.forEach($scope.playerCards, function (card) {
                        card.imgsrc = baseImgSrc + card.cardId + ".png"
                    });
                });  
            }
        }
        
        var getHandCards = function () 
        {
            if($scope.play) 
            {
                console.log("GetHandCards")
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
        
        $scope.getResults = function() 
        {
            Game.getResults(gameId).$promise.then(function(results) {
                $scope.results = results;
            });  
        }
                
        $scope.startGame = function ()
        {
            Game.update(gameId).$promise.then(function(game) {
                $scope.game = game; 
                $scope.pcgi = game.playerIds[playerId];
            }, function(error) {
                console.log(error)
            }); 
        }   
        
        $scope.playCard = function () 
        {
            console.log("card played")
            if($scope.selectedCard !== undefined) 
            {
                Player.update(playerId, $scope.selectedCard, false).$promise.then(function(card) {
                    
                }, function(error) {
                    console.log(error)
                    alert("Card cannot be placed");
                }); 
            }      
        }
        
        $scope.skipChance = function () 
        {
            Player.update(playerId, gameId, true).$promise.then(function(game) {
                console.log("chance skipped")
            }, function(error) {
                alert("Cards available to play");
                console.log(error)
                
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
              }, 3000);

        
        
    }
]);