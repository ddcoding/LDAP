(function() {
    'use strict';

    angular
        .module('ldapApp')
        .factory('ApplicationUserSearch', ApplicationUserSearch);

    ApplicationUserSearch.$inject = ['$resource'];

    function ApplicationUserSearch($resource) {
        var resourceUrl =  'api/_search/application-users/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
