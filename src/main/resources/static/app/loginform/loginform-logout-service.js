(function() {
    'use strict';

    angular
        .module('ldapApp')
        .factory('LogoutFactory', LoginFactory);

    LoginFactory.$inject = ['$resource'];

    function LoginFactory ($resource) {
        var resourceUrl =  '/logout';

        return $resource(resourceUrl);
    }
})();