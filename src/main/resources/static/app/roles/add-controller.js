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
        };
        vm.roles =[];
        vm.getAllRoles = function(){
            vm.roles = RoleFactory.query({},successGetAll,errorGetAll);
        };
        vm.getAllRoles();
        var successGetAll = function (data) {
            for(var i = 0; i< data.length; i++){
                vm.roles = [data.name];
            }
        };
        var errorGetAll = function () {
            alert("nie udalo sie pobrac")
        };
    }
})();