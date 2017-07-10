(function () {
    'use strict';
    angular
        .module('ldapApp')
        .controller('SearchingController', SearchingController);

    SearchingController.$inject = ['SendData'];

    function SearchingController(SendData) {
        var vm = this;
        vm.values = [];
        vm.result = [];
        vm.addFields = function () {
            vm.values[vm.values.length++] = [];
        };
        vm.getValues = function(){
            for(var i=0; i<vm.values.length;i++)
                console.log(vm.values[i]);
            if(vm.values.length === 0)
                alert("Nic nie wybrano!");
            else
            SendData.query({ldapFilters: vm.values},onSuccess,onError);
        };
        vm.delete = function () {
          vm.values.splice(vm.values.length-1,1);
        };
        function onSuccess(data) {
            vm.result = data;
        }
        function onError() {
            alert(" i nie wyszlo");
        }
    }

})();