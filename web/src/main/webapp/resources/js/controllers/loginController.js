var loginController = angular.module('loginController', [ 'ngRoute' ]).controller('loginController',['$scope', '$rootScope', '$location', 'Game', 'Player', function($scope, $rootScope, $location, Game, Player) {
                    'use strict';
        
        $scope.username = "";
        $scope.password = ""
        $rootScope.showLogin = $rootScope.playerId != null ? false : true;
        $rootScope.playGame = false;
        var baseImgSrc = "resources/img/cards/"; 
        var selc;
    
        $rootScope.clearAll = function () 
        {
            $rootScope.playerId = undefined;
            $rootScope.playGame = false;
            $rootScope.playerId = undefined;
            $rootScope.gameId = undefined;
        }
            
        $scope.doLogin = function ()
        {
            Player.query($scope.username).$promise.then(function(player) {
                $rootScope.playerId = player.id; 
                $rootScope.playerName = player.name;
                $rootScope.showLogin = false;
            });
        }
       
        var init = function () {
            Player.get(2, 1, 3).$promise.then(function(cards) {
                    $scope.playerCards = cards;
                    angular.forEach($scope.playerCards, function (card) {
                        card.imgsrc = baseImgSrc + card.cardId + ".png"
                    });
                }); 
            getHandCards();
            getResults();
            
        }
        
        var getResults = function() 
            {
                 
                Game.getResults(1).$promise.then(function(results) {
                    
                    $scope.results = results;
                });  
            }
        
        var getHandCards = function () 
        {
            
            {
                Game.getCards(1).$promise.then(function(cards) {
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
        
        $scope.selectCard =function (cardId)
        {
            console.log(cardId)
            $scope.selectedCard = cardId;
        }
        
        init();
    
    }
]);
