(function () {
    'use strict';
    angular
        .module('ldapApp')
        .controller('NavbarController', NavbarController);

    NavbarController.$inject = ['LogoutFactory','$rootScope','$state'];

    function NavbarController(LogoutFactory,$rootScope,$state) {
        var vm = this;

        vm.logOut = function () {
            LogoutFactory.save({},onSuccLogOut,onErrLogOut);
        };

        var onSuccLogOut = function () {
            $rootScope.authenticate = false;
            $state.go('login');
        };

        var onErrLogOut = function (status) {
            alert(status);
        };
        vm.isAuthenticate = function () {
            return $rootScope.authenticate
        };

        vm.checkAuthenticate = function () {
            if($rootScope.authenticate) alert("Połączenie nawiązane");
            else alert("Połączenie nieudane");
        };





    }
})();