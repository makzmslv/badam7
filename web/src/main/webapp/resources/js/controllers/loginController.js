var loginController = angular.module('loginController', [ 'ngRoute' ]).controller('loginController',['$scope', '$rootScope', '$location', 'Game', 'Player', function($scope, $rootScope, $location, Game, Player) {
                    'use strict';
        
        $scope.username = "";
        $scope.password = ""
        $rootScope.showLogin = $rootScope.playerId != null ? false : true;
        $rootScope.playGame = false;
    
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
    }
]);
