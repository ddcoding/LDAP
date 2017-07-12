(function() {
    'use strict';

    angular
        .module('ldapApp')
        .factory('LoginFactory', LoginFactory);

    LoginFactory.$inject = ['$resource'];

    function LoginFactory ($resource) {
        var resourceUrl =  'api/auth/login/:userName/:password';

        return $resource(resourceUrl);
    }
})();