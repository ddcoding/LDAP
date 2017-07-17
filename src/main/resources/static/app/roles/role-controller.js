(function () {
    'use strict';
    angular
        .module('ldapApp')
        .controller('RoleController', RoleController);

    RoleController.$inject = ['RoleFactory', 'Auth', '$rootScope'];

    function RoleController(RoleFactory, Auth, $rootScope) {
        var vm = this;
        //remove comment and comment few lines below cuz its test
        //-----------------uncomment--------------------
        // vm.roles =[];
        //-----------------end-uncomment----------------

        //--------------comment--------------
        vm.roles = [
            {name: 'USER', id: 1},
            {name: 'ADMIN', id: 2},
            {name: 'MOD', id: 3}];
        //-------------end-comment-----------

        Auth.authorize();
        vm.isAuthenticated = function () {
            return $rootScope.authenticate;
        };
        //-----------------uncomment--------------------
        // vm.getAllRoles = function(){
        //     vm.roles = RoleFactory.query({},successGetAll,errorGetAll);
        // };
        // vm.getAllRoles();
        // var successGetAll = function (data) {
        //     for(var i = 0; i< data.length; i++){
        //         vm.roles = [data.name];
        //     }
        // };
        // var errorGetAll = function () {
        //     alert("nie udalo sie pobrac")
        // };
        // vm.delete = function(role){
        //     RoleFactory.delete({roleParam:role},onSuccessDelete,onErrorDelete);
        // };
        // var onSuccessDelete = function () {
        //     console.log("Skasowano pomyslnie")
        // };
        // var onErrorDelete = function (satus) {
        //     console.log(status);
        // };
        // vm.edit = function (role) {
        //
        // }
        //-----------------end-uncomment----------------
    }
})();