angular.module('showMessageController', ['ngRoute'])
    .controller('showMessageController', function ($scope, $modalInstance, message) {
        'use strict';

        $scope.message = message;

       
        $scope.confirm = function () {
            $modalInstance.close('YES');
        };
    });