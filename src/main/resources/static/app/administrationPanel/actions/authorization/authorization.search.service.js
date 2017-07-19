(function() {
    'use strict';

    angular
        .module('ldapApp')
        .factory('AuthorizationSearch', AuthorizationSearch);

    AuthorizationSearch.$inject = ['$resource'];

    function AuthorizationSearch($resource) {
        var resourceUrl =  'api/_search/authorizations/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
