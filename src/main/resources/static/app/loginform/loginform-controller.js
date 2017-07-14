(function () {
    'use strict';
    angular
        .module('ldapApp')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['LoginFactory','$rootScope','$location','$state','Auth'];

    function LoginController(LoginFactory, $rootScope,$location,$state,Auth) {
        var vm = this;
        vm.user = {};
        vm.fullname = "BRAK!";
        Auth.authorize();
        vm.logIn = function () {
            if(vm.user.userName!=null && vm.user.password!=null) {
                LoginFactory.save({
                    userName: vm.user.userName,
                    password: vm.user.password
                }, onSuccessLogIn, onErrorLogIn);
            }
        };


        var onSuccessLogIn = function () {
            $rootScope.authenticate = true;
            $state.go('panel');
            // $location.state("panel");
        };
        var onErrorLogIn = function () {
            alert("Błędne dane logowania ! Spróbuj ponownie");
        };

        vm.isAuthenticate = function () {
            return $rootScope.authenticate
        };
    }
})();