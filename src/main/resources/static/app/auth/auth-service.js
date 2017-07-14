(function() {
    'use strict';

    angular
        .module('ldapApp')
        .factory('Auth', AuthFactory);

    AuthFactory.$inject = ['$resource','$rootScope'];

    function AuthFactory ($resource,$rootScope) {
        var resourceUrl =  'api/auth/check';

        var service = {
            authorize: authorize
            // login: login
            // logout: logout
        };

        return service;

        function authorize() {
            return $resource(resourceUrl).get({},function () {
                $rootScope.authenticate = true;
            },function () {
                $rootScope.authenticate = false;
            })
        }

    }
})();