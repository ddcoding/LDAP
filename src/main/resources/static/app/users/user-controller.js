(function () {
    'use strict';
    angular
        .module('ldapApp')
        .controller('UserController', UserController);

    UserController.$inject = ['UserFactory', 'Auth', '$rootScope'];

    function UserController(UserFactory, Auth, $rootScope) {
        var vm = this;
        //remove comment and comment few lines below cuz its test
        //-----------------uncomment--------------------
        // vm.users =[];
        //-----------------end-uncomment----------------

        //--------------comment--------------
        vm.users = [
            {name: 'jakdwo', id: 1},
            {name: 'kardro', id: 2},
            {name: 'bielel', id: 3}];
        //-------------end-comment-----------

        Auth.authorize();
        vm.isAuthenticated = function () {
            return $rootScope.authenticate;
        };
        //-----------------uncomment--------------------
        // vm.getAllUsers = function(){
        //     vm.users = UserFactory.query({},successGetAll,errorGetAll);
        // };
        // vm.getAllUsers();
        // var successGetAll = function (data) {
        //     for(var i = 0; i< data.length; i++){
        //         vm.users = [data.name];
        //     }
        // };
        // var errorGetAll = function () {
        //     alert("nie udalo sie pobrac")
        // };
        // vm.delete = function(role){
        //     UserFactory.delete({userParam:role},onSuccessDelete,onErrorDelete);
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