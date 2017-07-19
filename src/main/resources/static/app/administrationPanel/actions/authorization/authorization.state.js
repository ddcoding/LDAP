(function() {
    'use strict';

    angular
        .module('ldapApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('authorization', {
            parent: 'entity',
            url: '/authorization?page&sort&search',
            data: {
                // authorities: ['ROLE_USER'],
                pageTitle: 'Authorizations'
            },
            views: {
                'content@': {
                    templateUrl: 'app/administrationPanel/actions/authorization/authorizations.html',
                    controller: 'AuthorizationController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }]
            }
        })
        .state('authorization-detail', {
            parent: 'authorization',
            url: '/authorization/{id}',
            data: {
                // authorities: ['ROLE_USER'],
                pageTitle: 'Authorization'
            },
            views: {
                'content@': {
                    templateUrl: 'app/administrationPanel/actions/authorization/authorization-detail.html',
                    controller: 'AuthorizationDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Authorization', function($stateParams, Authorization) {
                    return Authorization.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: 'authorization',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('authorization-detail-edit', {
            parent: 'authorization-detail',
            url: '/detail/edit',
            data: {
                // authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/administrationPanel/actions/authorization/authorization-dialog.html',
                    controller: 'AuthorizationDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Authorization', function(Authorization) {
                            return Authorization.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('authorization-new', {
            parent: 'authorization',
            url: '/new',
            data: {
                // authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/administrationPanel/actions/authorization/authorization-dialog.html',
                    controller: 'AuthorizationDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                description: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('authorization', null, { reload: 'authorization' });
                }, function() {
                    $state.go('authorization');
                });
            }]
        })
        .state('authorization-edit', {
            parent: 'authorization',
            url: '/{id}/edit',
            data: {
                // authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/administrationPanel/actions/authorization/authorization-dialog.html',
                    controller: 'AuthorizationDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Authorization', function(Authorization) {
                            return Authorization.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('authorization', null, { reload: 'authorization' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('authorization-delete', {
            parent: 'authorization',
            url: '/{id}/delete',
            data: {
                // authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/administrationPanel/actions/authorization/authorization-delete-dialog.html',
                    controller: 'AuthorizationDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Authorization', function(Authorization) {
                            return Authorization.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('authorization', null, { reload: 'authorization' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
