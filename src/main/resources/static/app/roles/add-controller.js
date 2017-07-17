(function () {
    'use strict';
    angular
        .module('ldapApp')
        .controller('AddController', AddController);

    AddController.$inject = ['RoleFactory', 'Auth', '$rootScope'];

    function AddController(RoleFactory, Auth, $rootScope) {
        var vm = this;
        Auth.authorize();
        vm.values = [];
        vm.isAuthenticated = function(){
            return $rootScope.authenticate;
        };
        vm.save = function () {
            RoleFactory.update(vm.role, onSaveSuccess, onSaveError);
        };
        var onSaveSuccess = function (data) {
            console.log("sukces " + data.name);
        };
        var onSaveError = function () {
            console.log("błąd");
        }
    }
})();