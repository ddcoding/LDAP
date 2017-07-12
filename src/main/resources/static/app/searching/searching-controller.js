(function () {
    'use strict';
    angular
        .module('ldapApp')
        .controller('SearchingController', SearchingController);

    SearchingController.$inject = ['SendData', 'GetGroupsLdap'];

    function SearchingController(SendData, GetGroupsLdap) {
        var vm = this;
        vm.values = [];
        vm.result = [];
        vm.groups = [];
        vm.addFields = function () {
            vm.values[vm.values.length++] = [];
        };

        vm.getName();

        vm.getGroups = function () {
            GetGroupsLdap.query({}, onSuccessGroups, onErrorGroups)
        };

        vm.getGroups();
        function onSuccessGroups(data) {
            vm.groups = data;
        }

        function onErrorGroups(status) {
            alert("Wystapil problem ze statusem: " + status);
        }

        vm.getValues = function () {
            for (var i = 0; i < vm.values.length; i++)
                console.log(vm.values[i]);
            if (vm.values.length === 0)
                alert("Nic nie wybrano!");
            else
                SendData.query({ldapFilters: vm.values}, onSuccess, onError);
        };
        vm.delete = function () {
            vm.values.splice(vm.values.length - 1, 1);
        };
        function onSuccess(data) {
            vm.result = data;
        }

        function onError() {
            alert(" i nie wyszlo");
        }
    }

})();