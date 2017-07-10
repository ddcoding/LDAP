(function() {
    'use strict';

    angular
        .module('ldapApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
            .state('searching', {
                parent: 'app',
                url: '/home',
                data: {
                    // pageTitle: 'jhipsterSampleApplicationApp.operation.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'app/searching/searching.html',
                        controller: 'SearchingController',
                        controllerAs: 'vm'
                    }
                },
                resolve: {
                }
            })
    }
})();/**
 * Created by kardro on 10.07.2017.
 */
