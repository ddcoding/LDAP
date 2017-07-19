(function() {
    'use strict';

    angular
        .module('ldapApp')
        .controller('ApplicationUserDetailController', ApplicationUserDetailController);

    ApplicationUserDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'ApplicationUser', 'UserGroup', 'Role','Auth'];

    function ApplicationUserDetailController($scope, $rootScope, $stateParams, previousState, entity, ApplicationUser, UserGroup, Role, Auth) {
        Auth.authorize();
        var vm = this;

        vm.applicationUser = entity;
        vm.previousState = previousState.name;


        var unsubscribe = $rootScope.$on('ldapApp:applicationUserUpdate', function(event, result) {
            vm.applicationUser = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
