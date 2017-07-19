(function() {
    'use strict';
    angular
        .module('robimytov2App')
        .factory('Authorization', Authorization);

    Authorization.$inject = ['$resource'];

    function Authorization ($resource) {
        var resourceUrl =  'api/authorizations/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
