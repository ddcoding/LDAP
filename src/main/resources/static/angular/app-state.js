(function() {
    'use strict';

    angular
        .module('ldapApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider','$locationProvider'];

    function stateConfig($stateProvider,$locationProvider) {
        $locationProvider.hashPrefix('');
        // $locationProvider.html5Mode(true); // uncomment it if u want to remove # from url and remember to add <base> to ur head statement
        $stateProvider.state('app', {
            abstract: true,
            resolve: {
                //auth
            }
        });
    }
})();