(function() {
    'use strict';

    angular
        .module('ldapApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider','unsavedWarningsConfigProvider'];

    function stateConfig($stateProvider,unsavedWarningsConfigProvider) {
        $stateProvider
        .state('application-user', {
            parent: 'entity',
            url: '/application-user?page&sort&search',
            data: {
                // authorities: ['ROLE_USER'],
                pageTitle: 'panel'
            },
            views: {
                'content@': {
                    templateUrl: 'app/administrationPanel/actions/application-user/application-users.html',
                    controller: 'ApplicationUserController',
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
        .state('application-user-detail', {
            parent: 'application-user',
            url: '/application-user/{id}',
            data: {
                // authorities: ['ROLE_USER'],
                pageTitle: 'User details'
            },
            views: {
                'content@': {
                    templateUrl: 'app/administrationPanel/actions/application-user/application-user-detail.html',
                    controller: 'ApplicationUserDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'ApplicationUser', function($stateParams, ApplicationUser) {
                    return ApplicationUser.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'application-user',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('application-user-detail-edit', {
            parent: 'application-user-detail',
            url: '/detail/edit',
            data: {
                // authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/administrationPanel/actions/application-user/application-user-dialog.html',
                    controller: 'ApplicationUserDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ApplicationUser', function(ApplicationUser) {
                            return ApplicationUser.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('application-user-new', {
            parent: 'application-user',
            url: '/new',
            data: {
                // authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/administrationPanel/actions/application-user/application-user-dialog.html',
                    controller: 'ApplicationUserDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                branch: null,
                                name: null,
                                login: null,
                                surname: null,
                                position: null,
                                email: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('application-user', null, { reload: 'application-user' });
                }, function() {
                    $state.go('application-user');
                });
            }]
        })
        .state('application-user-edit', {
            parent: 'application-user',
            url: '/{id}/edit',
            data: {
                // authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/administrationPanel/actions/application-user/application-user-dialog.html',
                    controller: 'ApplicationUserDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ApplicationUser', function(ApplicationUser) {
                            return ApplicationUser.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('application-user', null, { reload: 'application-user' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('application-user-delete', {
            parent: 'application-user',
            url: '/{id}/delete',
            data: {
                // authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/administrationPanel/actions/application-user/application-user-delete-dialog.html',
                    controller: 'ApplicationUserDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['ApplicationUser', function(ApplicationUser) {
                            return ApplicationUser.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('application-user', null, { reload: 'application-user' });
                }, function() {
                    $state.go('^');
                });
            }]
        });

        unsavedWarningsConfigProvider.navigateMessage="Masz niezapisane zmiany w formularzu, czy na pewno chcesz opuścić stronę?";
        unsavedWarningsConfigProvider.routeEvent='$locationChangeStart';
    }

})();
