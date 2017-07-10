(function () {
    'use strict';
    angular
        .module('ldapApp')
        .controller('SearchingController', SearchingController);

    SearchingController.$inject = ['SendData'];

    function SearchingController(SendData) {
        var vm = this;
        vm.hey = "Hello world ! ";
        vm.values = [];
        vm.addFields = function () {
            vm.values[vm.values.length++] = [];
        };
        vm.getValues = function(){
            for(var i=0; i<vm.values.length;i++)
                console.log(vm.values[i]);
            SendData.query({ldapFilters: vm.values},onSuccess,onError);
        };
        vm.delete = function () {
          vm.values.splice(vm.values.length-1,1);
        };
        function onSuccess(data) {
            vm.ldapFilteredList = data;
            alert(data);
        }
        function onError() {
            alert(" i nie wyszlo");
        }
    }

})();