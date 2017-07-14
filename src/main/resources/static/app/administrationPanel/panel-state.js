(function() {
    'use strict';

    angular
        .module('ldapApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
            .state('panel', {
                parent: 'app',
                url: '/administration',
                data: {
                    authorities: ['ROLE_USER']
                    // pageTitle: 'jhipsterSampleApplicationApp.operation.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'app/administrationPanel/panel.html',
                        controller: 'AdministrationController',
                        controllerAs: 'vm'
                    }
                },
                resolve: {
                }
            })
    }
})();
