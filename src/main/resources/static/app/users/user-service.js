(function() {
    'use strict';

    angular
        .module('ldapApp')
        .factory('UserFactory', UserFactory);

    UserFactory.$inject = ['$resource'];

    function UserFactory ($resource) {
        var resourceUrl =  'api/users/:userParam:id';

        return $resource(resourceUrl,{},
            {'update': { method:'PUT' }});
    }
})();