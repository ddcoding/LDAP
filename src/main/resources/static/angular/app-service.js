(function() {
    'use strict';

    angular
        .module('ldapApp')
        .factory('SendData', SendData);

    SendData.$inject = ['$resource'];

    function SendData ($resource) {
        var resourceUrl =  'api/filters/:ldapFilters';

        return $resource(resourceUrl);
    }
})();