(function() {
    'use strict';

    angular
        .module('ldapApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
            .state('login', {
                parent: 'app',
                url: '/login',
                data: {
                },
                views: {
                    'content@': {
                        templateUrl: 'app/loginform/loginform.html',
                        controller: 'LoginController',
                        controllerAs: 'vm'
                    }
                },
                resolve: {
                }
            })
    }
})();
