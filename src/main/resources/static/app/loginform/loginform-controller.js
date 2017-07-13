(function () {
    'use strict';
    angular
        .module('ldapApp')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['LoginFactory','$rootScope','$location','NameService','LogoutFactory'];

    function LoginController(LoginFactory, $rootScope,$location,NameService,LogoutFactory) {
        var vm = this;
        vm.credentials = {};
        vm.fullname = "BRAK!";
        vm.logIn = function () {
            if(vm.credentials.userName!=null && vm.credentials.password!=null) {
                LoginFactory.query({
                    userName: vm.credentials.userName,
                    password: vm.credentials.password
                }, onSuccess, onError);
            }
        };

        vm.logOut = function () {
            LogoutFactory.save({},onSuccLogOut,onErrLogOut);
        };

        var onSuccLogOut = function () {
        $rootScope.authenticate = false;
        vm.fullname = "BRAK";
        };

        var onErrLogOut = function (status) {
            alert(status);
        };

        var onSuccess = function () {
            $rootScope.authenticate = true;
            vm.getName();
        };
        var onError = function () {
            alert("Błędne dane logowania ! Spróbuj ponownie");
        };

        vm.isAuthenticate = function () {
            return $rootScope.authenticate ;
        };


        vm.getName = function () {
            NameService.query({},onSucc,onErr);
        };

        function onSucc(data) {
            $rootScope.authenticate = true;
            vm.fullname = data;
        }

        function onErr(status)
        {
            $rootScope.authenticate = false;
        }

        vm.getName();



    }
})();