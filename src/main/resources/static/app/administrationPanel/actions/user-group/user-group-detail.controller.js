(function() {
    'use strict';

    angular
        .module('ldapApp')
        .controller('UserGroupDetailController', UserGroupDetailController);

    UserGroupDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'UserGroup', 'Role', 'ApplicationUser'];

    function UserGroupDetailController($scope, $rootScope, $stateParams, previousState, entity, UserGroup, Role, ApplicationUser) {
        var vm = this;

        vm.userGroup = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('ldapApp:userGroupUpdate', function(event, result) {
            vm.userGroup = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
