(function () {
    'use strict';

    angular
        .module('ldapApp')
        .service('AuthFactory', AuthFactory);

    AuthFactory.$inject = ['$http'];

    function AuthFactory($http) {
        var resourceUrl = 'api/auth/authenticate';
        this.authenticate = function (credentials, successCallback) {
            var authHeader = {Authorization: 'Basic ' + btoa(credentials.userName + ':' + null)};
            var config = {headers: authHeader};
            $http.post(resourceUrl, {}, config)
                .then(function success(value) {
                    successCallback();
                }, function error(reason) {
                    console.log('Login error');
                    console.log(reason);
                })
        };
    }
})();