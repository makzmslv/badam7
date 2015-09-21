'use strict';

/* Services */

var badam7Services = angular.module('badam7Services', [ 'ngResource' ]);



badam7Services.factory('Game', function($resource) {
    "use strict";
        return {
            "query" : function (parameters) {
                var ret, restResource;
                restResource = $resource('/badam7-web/games', {}, {
                    "query": {
                        "method" : "GET",
                        "isArray" : true
                    }
                });
                ret = restResource.query(parameters);
                return ret;
            },
            "update" : function (id) {
                var ret, restResource;
                restResource = $resource('/badam7-web/games:id', {}, {
                    update : {
                        method : 'PUT'
                    }
                });
                ret = restResource.update({id : id});
                return ret;
            },
            "save" : function (requestBody) {
                var ret, restResource;
                restResource = $resource('/badam7-web/games', {}, {});

                ret = restResource.save(requestBody);

                return ret;
            },
            "get" : function (id) {
                var ret, restResource;
                restResource = $resource('/badam7-web/games/:id', {}, {
                    "get": {
                        "method" : "GET",
                        "isArray" : false
                    }
                });
                ret = restResource.get({ id : id});
                return ret;
            }
        };
});

badam7Services.factory('Player', function($resource) {
    "use strict";
        return {
            "query" : function (parameters) {
                var ret, restResource;
                restResource = $resource('/badam7-web/players', {}, {
                    "query": {
                        "method" : "GET",
                        "isArray" : true
                    }
                });
                ret = restResource.query(parameters);
                return ret;
            },
            "update" : function (id, currentHandId) {
                var ret, restResource;
                restResource = $resource('/badam7-web/players/:id/playercurrentgameinstance', {currentHandId : currentHandId}, {
                    update : {
                        method : 'PUT'
                    }
                });
                ret = restResource.update({ id : id});
                return ret;
            },
            "save" : function (id, gameId) {
                var ret, restResource;
                restResource = $resource('/badam7-web/players/:id/playercurrentgameinstance', {gameId : gameId}, {
                    "save": {
                        "method" : "POST"
                    }
                    });

                ret = restResource.save({id : id});

                return ret;
            },
            "get" : function (id, currentHandId, playerCurrentGameInstanceId) {
                var ret, restResource;
                restResource = $resource('/badam7-web/players/:id/playercurrentgameinstance', {currentHandId : currentHandId, playerCurrentGameInstanceId : playerCurrentGameInstanceId}, {
                    "get": {
                        "method" : "GET",
                        "isArray" : true
                    }
                });
                ret = restResource.get({ id : id});
                return ret;
            }
        };
});

