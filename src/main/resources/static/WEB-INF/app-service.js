(function() {
    'use strict';
    angular
        .module('ldapApp')
        .factory('SendData', SendData);

    SendData.$inject = ['$resource'];

    function SendData ($resource) {
        var resourceUrl =  'api/filters';

        return $resource(resourceUrl, {}, {
            'get': { method: 'GET', isArray: true},
            'update': { method:'PUT' }
        });
    }
})();