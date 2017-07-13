(function () {
    'use strict';
    angular
        .module('ldapApp')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['LoginFactory','$rootScope','$location','LogoutFactory'];

    function LoginController(LoginFactory, $rootScope,$location,LogoutFactory) {
        var vm = this;
        vm.user = {};
        vm.fullname = "BRAK!";
        vm.logIn = function () {
            if(vm.user.userName!=null && vm.user.password!=null) {
                LoginFactory.save({
                    userName: vm.user.userName,
                    password: vm.user.password
                }, onSuccessLogIn, onErrorLogIn);
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

        var onSuccessLogIn = function () {
            $rootScope.authenticate = true;
            vm.getName();
        };
        var onErrorLogIn = function () {
            alert("Błędne dane logowania ! Spróbuj ponownie");
        };

        vm.isAuthenticate = function () {
            return $rootScope.authenticate
        };

        vm.checkAuthenticate = function () {
            if($rootScope.authenticate) alert("Połączenie nawiązane");
            else alert("Połączenie nieudane");
        };


        vm.getName = function () {
            LoginFactory.query({},onGetNameSuccess,onGetNameError);
        };

        function onGetNameSuccess(data) {
            $rootScope.authenticate = true;
            vm.fullname = data;
        }

        function onGetNameError(status)
        {
            $rootScope.authenticate = false;
        }

        vm.getName();



    }
})();