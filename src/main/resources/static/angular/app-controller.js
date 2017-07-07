(function() {
    'use strict';

    angular
        .module('ldapApp')
        .controller('MainController', MainController);

    MainController.$inject = ['SendData'];

    function MainController(SendData) {
       var vm = this;
       vm.ldapFilteredList = null;
       vm.ldapToFilterList = null;
       var getData = function () {
           SendData.query({
               ldapFilters: vm.ldapToFilterList
           },onSuccess,onError);
       };
       function onSuccess(data) {
           alert("sukces");
            vm.ldapFilteredList = data;
            console.log("data: " + data);
       }
       function onError() {
            alert("chuj i nie wyszlo");
       }
       getData();
    }
})();