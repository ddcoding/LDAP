(function () {
    'use strict';
    angular
        .module('ldapApp')
        .controller('AdministrationController', AdministrationController);

    AdministrationController.$inject = ['LoginFactory','$rootScope','Auth'];

    function AdministrationController(LoginFactory,$rootScope,Auth) {
        var vm = this;
        vm.fullname = ["Niezalogowany użytkowniku!"];
        Auth.authorize();

        vm.getName = function () {
            LoginFactory.query({},onGetNameSuccess,onGetNameError);
        };

        function onGetNameSuccess(data) {
            vm.fullname = data;
        }

        function onGetNameError(status)
        {

        }

        vm.getName();

    }
})();