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
                restResource = $resource('/badam7-web/games/:id', {id:'@id'}, {
                    update : {
                        method : 'POST'
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
                restResource = $resource('/badam7-web/games/:id', {id:'@id'}, {
                    "get": {
                        "method" : "GET",
                        "isArray" : false
                    }
                });
                ret = restResource.get({ id : id});
                return ret;
            },            
            "getCards" : function (handId) {
                var ret, restResource;
                restResource = $resource('/badam7-web/games/hand', {handId : handId}, {
                    "get": {
                        "method" : "GET",
                        "isArray" : false
                    }
                });
                ret = restResource.get();
                return ret;
            }
        };
});

badam7Services.factory('Player', function($resource) {
    "use strict";
        return {
            "query" : function (username) {
                var ret, restResource;
                restResource = $resource('/badam7-web/players', {username : username}, {
                    "query": {
                        "method" : "GET",
                        "isArray" : false
                    }
                });
                ret = restResource.query();
                return ret;
            },
            "update" : function (id, playerCurrentHandCardId, skipChance) {
                var ret, restResource;
                restResource = $resource('/badam7-web/players/:id/playercurrentgameinstance', {id:'@id', playerCurrentHandCardId : playerCurrentHandCardId, skipChance : skipChance}, {
                    update : {
                        method : 'PUT'
                    }
                });
                ret = restResource.update({ id : id});
                return ret;
            },
            "save" : function (id, gameId) {
                var ret, restResource;
                restResource = $resource('/badam7-web/players/playercurrentgameinstance', {id : id, gameId : gameId}, {
                    "save": {
                        "method" : "POST"
                    }
                    });

                ret = restResource.save();

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

