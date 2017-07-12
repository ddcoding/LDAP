(function() {
    'use strict';

    angular
        .module('ldapApp')
        .factory('GetGroupsLdap', SendData);

    SendData.$inject = ['$resource'];

    function SendData ($resource) {
        var resourceUrl =  'api/ldap/groups';

        return $resource(resourceUrl);
    }
})();