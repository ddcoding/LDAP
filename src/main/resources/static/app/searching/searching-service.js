(function() {
    'use strict';

    angular
        .module('ldapApp')
        .factory('SendData', SendData);

    SendData.$inject = ['$resource'];

    function SendData ($resource) {
        var resourceUrl =  'api/ldap/filters/:ldapFilters';

        return $resource(resourceUrl);
    }
})();