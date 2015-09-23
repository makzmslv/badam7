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
                $rootScope.showLogin = false;
            });
        }
        
       $scope.$on('$locationChangeStart', function( event ) {
            var answer = confirm("Are you sure you want to leave this page?")
            if (!answer) {
                event.preventDefault();
            }
        });  
    
        var init = function () {
            Player.get(3, 1, 2).$promise.then(function(cards) {
                
                        $scope.playerCards = cards;
                        angular.forEach($scope.playerCards, function (card) {
                            card.imgsrc = baseImgSrc + card.cardId + ".png"
                        });
                    }); 
        }
        
        $scope.selectCard =function (cardId)
        {
            console.log(cardId)
            $scope.selectedCard = cardId;
        }
        
        
    
    }
]);
