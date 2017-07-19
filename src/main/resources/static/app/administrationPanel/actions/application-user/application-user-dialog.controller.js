(function() {
    'use strict';

    angular
        .module('ldapApp')
        .controller('ApplicationUserDialogController', ApplicationUserDialogController);

    ApplicationUserDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'ApplicationUser', 'UserGroup', 'Role'];

    function ApplicationUserDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, ApplicationUser, UserGroup, Role) {
        var vm = this;

        vm.applicationUser = entity;
        vm.clear = clear;
        vm.save = save;
        vm.usergroups = UserGroup.query();
        vm.roles = Role.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        $scope.$on('modal.closing', function(event, reason, closed) {
            // console.log('modal.closing: ' + (closed ? 'close' : 'dismiss') + '(' + reason + ')');
            // var message = "You are about to leave the edit view. Uncaught reason. Are you sure?";
            // switch (reason){
            //     // clicked outside
            //     case "backdrop click":
            //         message = "Any changes will be lost, are you sure?";
            //         break;
            //
            //     // cancel button
            //     case "cancel":
            //         message = "Any changes will be lost, are you sure?";
            //         break;
            //
            //     // escape key
            //     case "escape key press":
            //         message = "Any changes will be lost, are you sure?";
            //         break;
            // }
            // if (!confirm(message)) {
                event.preventDefault();
            // }
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.applicationUser.id !== null) {
                ApplicationUser.update(vm.applicationUser, onSaveSuccess, onSaveError);
            } else {
                ApplicationUser.save(vm.applicationUser, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('ldapApp:applicationUserUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
