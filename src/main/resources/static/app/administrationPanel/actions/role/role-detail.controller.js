(function() {
    'use strict';

    angular
        .module('robimytov2App')
        .controller('RoleDetailController', RoleDetailController);

    RoleDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Role', 'Authorization', 'ApplicationUser', 'UserGroup'];

    function RoleDetailController($scope, $rootScope, $stateParams, previousState, entity, Role, Authorization, ApplicationUser, UserGroup) {
        var vm = this;

        vm.role = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('robimytov2App:roleUpdate', function(event, result) {
            vm.role = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
