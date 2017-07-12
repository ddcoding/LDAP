(function () {
    'use strict';
    angular
        .module('ldapApp')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['LoginFactory'];

    function LoginController(LoginFactory) {
        var vm = this;
        vm.fullname = "nazwisko";
        vm.logIn = function () {
            if(vm.userName!=null && vm.password!=null)
            LoginFactory.query({
                userName: vm.userName,
                password: vm.password
            }, onSuccess, onError);
        };
        vm.logIn();
        var onSuccess = function (data) {
            vm.fullname = data;
        };
        var onError = function () {
            alert("nie pyklo");
        };


    }
})();