(function() {
    'use strict';

    angular
        .module('ldapApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('app', {
            abstract: true,
            views: {
                'index@': {
                    templateUrl: 'WEB-INF/views/index.html',
                    controller: 'MainController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                //auth
            }
        });
    }
})();