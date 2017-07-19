(function() {
    'use strict';

    angular
        .module('ldapApp')
        .controller('ApplicationUserDetailController', ApplicationUserDetailController);

    ApplicationUserDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'ApplicationUser', 'Role', 'UserGroup'];

    function ApplicationUserDetailController($scope, $rootScope, $stateParams, previousState, entity, ApplicationUser, Role, UserGroup) {
        var vm = this;

        vm.applicationUser = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('ldapApp:applicationUserUpdate', function(event, result) {
            vm.applicationUser = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
