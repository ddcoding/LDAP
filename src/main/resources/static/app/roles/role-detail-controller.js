(function () {
    'use strict';
    angular
        .module('ldapApp')
        .controller('RoleDetailController', RoleDetailController);

    RoleDetailController.$inject = ['$timeout', '$scope', '$stateParams', 'entity', 'RoleFactory','$state','$rootScope','Auth'];

    function RoleDetailController($timeout, $scope, $stateParams, entity, RoleFactory,$state,$rootScope,Auth) {
        var vm=this;
        vm.role = entity;
        vm.clear = clear;
        vm.save = save;
        // vm.operations = Operation.query();
        Auth.authorize();
        vm.isAuthenticated = function(){
            return $rootScope.authenticate;
        };

        $timeout(function (){
            // angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $state.go('roles');
        }

        function save () {
            vm.isSaving = true;
            if (vm.role.id !== null) {
                RoleFactory.update(vm.role, onSaveSuccess, onSaveError);
            } else {
                RoleFactory.save(vm.role, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            console.log("name: " + result.name);
            // $scope.$emit('ldapApp:roleUpdate', result);
            // $uibModalInstance.close(result);
            // vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();