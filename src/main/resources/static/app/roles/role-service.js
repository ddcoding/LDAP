(function() {
    'use strict';

    angular
        .module('ldapApp')
        .factory('RoleFactory', RoleFactory);

    RoleFactory.$inject = ['$resource'];

    function RoleFactory ($resource) {
        var resourceUrl =  'api/roles/:roleParam:id';

        return $resource(resourceUrl,{},
            {'update': { method:'PUT' }});
    }
})();