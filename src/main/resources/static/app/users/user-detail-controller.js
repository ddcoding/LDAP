(function () {
    'use strict';
    angular
        .module('ldapApp')
        .controller('UserDetailController', UserDetailController);

    UserDetailController.$inject = ['$timeout', '$scope', '$stateParams', 'entity', 'UserFactory','$state','$rootScope','Auth'];

    function UserDetailController($timeout, $scope, $stateParams, entity, UserFactory,$state,$rootScope,Auth) {
        var vm=this;
        vm.user = entity;
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
            $state.go('users');
        }

        function save () {
            vm.isSaving = true;
            if (vm.user.id !== null) {
                UserFactory.update(vm.user, onSaveSuccess, onSaveError);
            } else {
                UserFactory.save(vm.user, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            console.log("name: " + result.name);
            // $scope.$emit('ldapApp:userUpdate', result);
            // $uibModalInstance.close(result);
            // vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();