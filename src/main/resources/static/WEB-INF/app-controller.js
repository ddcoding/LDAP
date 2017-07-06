(function() {
    'use strict';

    angular
        .module('ldapApp')
        .controller('MainController', MainController);

    MainController.$inject = ['SendData'];

    function MainController(SendData) {

       var vm = this;
       vm.ldapFilter = null;
       var getData = function () {
           vm.ldapFilter = SendData.get();
       }
    }
})();