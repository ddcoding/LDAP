(function() {
    'use strict';

    angular
        .module('ldapApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
            .state('roles', {
                parent: 'app',
                url: '/roles',
                data: {
                    authorities: ['ROLE_USER']
                    // pageTitle: 'jhipsterSampleApplicationApp.operation.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'app/roles/roles.html',
                        controller: 'RoleController',
                        controllerAs: 'vm'
                    }
                },
                resolve: {
                }
            })
            .state('addRole',{
                parent: 'app',
                url: '/add/role',
                data: {
                    authorities: ['ROLE_USER']
                    // pageTitle: 'jhipsterSampleApplicationApp.operation.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'app/roles/add.html',
                        controller: 'AddController',
                        controllerAs: 'vm'
                    }
                }
            })
            .state('role-detail', {
                parent: 'roles',
                url: '/add/role/{id}',
                data: {
                    authorities: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'app/roles/role-detail.html',
                        controller: 'RoleDetailController',
                        controllerAs: 'vm'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'RoleFactory', function($stateParams, RoleFactory) {
                        return RoleFactory.get({id : $stateParams.id}).$promise;
                    }],
                    previousState: ["$state", function ($state) {
                        var currentStateData = {
                            name: $state.current.name || 'roles',
                            params: $state.params,
                            url: $state.href($state.current.name, $state.params)
                        };
                        return currentStateData;
                    }]
                }
            })
    }
})();
