(function() {
    'use strict';

    angular
        .module('ldapApp')
        .controller('ApplicationUserDeleteController',ApplicationUserDeleteController);

    ApplicationUserDeleteController.$inject = ['$uibModalInstance', 'entity', 'ApplicationUser'];

    function ApplicationUserDeleteController($uibModalInstance, entity, ApplicationUser) {
        var vm = this;

        vm.applicationUser = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            ApplicationUser.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
