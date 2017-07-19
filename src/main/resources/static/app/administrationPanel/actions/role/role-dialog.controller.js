(function() {
    'use strict';

    angular
        .module('ldapApp')
        .controller('RoleDialogController', RoleDialogController);

    RoleDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Role', 'Authorization', 'ApplicationUser', 'UserGroup'];

    function RoleDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Role, Authorization, ApplicationUser, UserGroup) {
        var vm = this;

        vm.role = entity;
        vm.clear = clear;
        vm.save = save;
        vm.authorizations = Authorization.query();
        vm.roles = Role.query();
        vm.applicationusers = ApplicationUser.query();
        vm.usergroups = UserGroup.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.role.id !== null) {
                Role.update(vm.role, onSaveSuccess, onSaveError);
            } else {
                Role.save(vm.role, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('ldapApp:roleUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
