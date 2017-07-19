(function() {
    'use strict';

    angular
        .module('ldapApp')
        .controller('RoleDetailController', RoleDetailController);

    RoleDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Role', 'Authorization', 'ApplicationUser', 'UserGroup','Auth'];

    function RoleDetailController($scope, $rootScope, $stateParams, previousState, entity, Role, Authorization, ApplicationUser, UserGroup,Auth) {
        Auth.authorize();
        var vm = this;

        vm.role = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('ldapApp:roleUpdate', function(event, result) {
            vm.role = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();