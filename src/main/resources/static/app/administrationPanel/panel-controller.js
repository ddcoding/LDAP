(function () {
    'use strict';
    angular
        .module('ldapApp')
        .controller('AdministrationController', AdministrationController);

    AdministrationController.$inject = ['LoginFactory', '$rootScope', 'Auth', '$state', '$scope'];

    function AdministrationController(LoginFactory, $rootScope, Auth, $state, $scope) {
        Auth.authorize();
        var vm = this;
        vm.fullname = ["Niezalogowany użytkowniku!"];
        vm.roles = [];
        vm.getName = function () {
            LoginFactory.query({}, onGetNameSuccess, onGetNameError);
        };
        vm.getName();

        function onGetNameSuccess(data) {
            vm.fullname = data;
        }

        function onGetNameError(status) {

        }

    }
})();