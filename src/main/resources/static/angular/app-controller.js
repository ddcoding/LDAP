(function() {
    'use strict';

    angular
        .module('ldapApp')
        .controller('MainController', MainController);

    MainController.$inject = ['SendData'];

    function MainController(SendData) {
       var vm = this;
       vm.ldapFilteredList = null;
       var ldapFilter = {attribute: "cu", value: "Jan"};
       var ldapFilter2 = {attribute: "cu", value: "Iwan"};
       var ldapFiltered = ldapFilter.attribute + " " + ldapFilter.value;
       var ldapFiltered2 = ldapFilter2.attribute + " " + ldapFilter2.value;
       vm.ldapToFilterList = [ldapFiltered, ldapFiltered2];
       var getData = function () {
           // SendData.query({
           //     ldapFilters: vm.ldapToFilterList
           // },onSuccess,onError);
           var chuj = "jestem tutaj zerem";
           SendData.get({ldapFilters: vm.ldapToFilterList},onSuccess,onError);
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