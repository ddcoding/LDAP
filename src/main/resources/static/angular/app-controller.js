(function() {
    'use strict';

    angular
        .module('ldapApp')
        .controller('MainController', MainController);

    MainController.$inject = ['SendData'];

    function MainController(SendData) {
       var vm = this;
       var attribute = "cn", value = "Jan";
       vm.ldapList = [attribute, value];
       var getData = function () {
           SendData.query({ldapFilters: vm.ldapList},onSuccess,onError);
       };
       function onSuccess(data) {
            vm.ldapFilteredList = data;
       }
       function onError() {
            alert(" i nie wyszlo");
       }
       getData();
    }
})();