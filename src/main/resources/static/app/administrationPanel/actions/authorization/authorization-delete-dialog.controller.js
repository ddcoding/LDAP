(function() {
    'use strict';

    angular
        .module('robimytov2App')
        .controller('AuthorizationDeleteController',AuthorizationDeleteController);

    AuthorizationDeleteController.$inject = ['$uibModalInstance', 'entity', 'Authorization'];

    function AuthorizationDeleteController($uibModalInstance, entity, Authorization) {
        var vm = this;

        vm.authorization = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Authorization.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
