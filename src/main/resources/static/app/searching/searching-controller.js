(function() {
    'use strict';
    angular
        .module('ldapApp')
        .controller('SearchingController',SearchingController);

    SearchingController.$inject = [];

    function SearchingController() {

        var vm = this ;
        vm.hey = "Hello world ! ";

    }



})();