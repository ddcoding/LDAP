(function() {
    'use strict';

    angular
        .module('ldapApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
            .state('users', {
                parent: 'app',
                url: '/users',
                data: {
                    authorities: ['ROLE_USER']
                    // pageTitle: 'jhipsterSampleApplicationApp.operation.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'app/users/user.html',
                        controller: 'UserController',
                        controllerAs: 'vm'
                    }
                },
                resolve: {
                }
            })
            .state('addUser',{
                parent: 'app',
                url: '/add/user',
                data: {
                    authorities: ['ROLE_USER']
                    // pageTitle: 'jhipsterSampleApplicationApp.operation.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'app/users/user-add.html',
                        controller: 'AddController',
                        controllerAs: 'vm'
                    }
                }
            })
            .state('user-detail', {
                parent: 'users',
                url: '/add/user/{id}',
                data: {
                    authorities: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'app/users/user-detail.html',
                        controller: 'UserDetailController',
                        controllerAs: 'vm'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'UserFactory', function($stateParams, RoleFactory) {
                        return RoleFactory.get({id : $stateParams.id}).$promise;
                    }],
                    previousState: ["$state", function ($state) {
                        var currentStateData = {
                            name: $state.current.name || 'users',
                            params: $state.params,
                            url: $state.href($state.current.name, $state.params)
                        };
                        return currentStateData;
                    }]
                }
            })
    }
})();
