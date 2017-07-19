(function() {
    'use strict';

    angular
        .module('ldapApp')
        .controller('AuthorizationDialogController', AuthorizationDialogController);

    AuthorizationDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Authorization', 'Role'];

    function AuthorizationDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Authorization, Role) {
        var vm = this;

        vm.authorization = entity;
        vm.clear = clear;
        vm.save = save;
        vm.roles = Role.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.authorization.id !== null) {
                Authorization.update(vm.authorization, onSaveSuccess, onSaveError);
            } else {
                Authorization.save(vm.authorization, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('ldapApp:authorizationUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
