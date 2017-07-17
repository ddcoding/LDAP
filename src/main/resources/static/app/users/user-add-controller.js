(function () {
    'use strict';
    angular
        .module('ldapApp')
        .controller('AddUserController', AddUserController);

    AddUserController.$inject = ['RoleFactory', 'Auth', '$rootScope'];

    function AddUserController(RoleFactory, Auth, $rootScope) {
        var vm = this;
        Auth.authorize();
        vm.values = [];
        vm.isAuthenticated = function(){
            return $rootScope.authenticate;
        };
        vm.save = function () {
            UserFactory.update(vm.user, onSaveSuccess, onSaveError);
        };
        var onSaveSuccess = function (data) {
            console.log("sukces " + data.name);
        };
        var onSaveError = function () {
            console.log("błąd");
        }

    }
})();