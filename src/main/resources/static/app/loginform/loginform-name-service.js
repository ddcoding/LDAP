(function() {
    'use strict';

    angular
        .module('ldapApp')
        .factory('NameService', NameService);

    NameService.$inject = ['$resource'];

    function NameService ($resource) {
        var resourceUrl =  'api/auth/islogged';

        return $resource(resourceUrl);
    }
})();