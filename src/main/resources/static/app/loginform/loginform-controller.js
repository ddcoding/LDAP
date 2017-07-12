(function () {
    'use strict';
    angular
        .module('ldapApp')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['LoginFactory'];

    function LoginController(LoginFactory) {
        var vm = this;
        vm.user = null;
        vm.logIn = function (data) {
            LoginFactory.save(data);
        }

    }
    })();