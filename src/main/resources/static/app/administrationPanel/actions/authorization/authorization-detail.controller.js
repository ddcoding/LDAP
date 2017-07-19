(function() {
    'use strict';

    angular
        .module('ldapApp')
        .controller('AuthorizationDetailController', AuthorizationDetailController);

    AuthorizationDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Authorization', 'Role','Auth'];

    function AuthorizationDetailController($scope, $rootScope, $stateParams, previousState, entity, Authorization, Role, Auth) {
        Auth.authorize();
        var vm = this;

        vm.authorization = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('ldapApp:authorizationUpdate', function(event, result) {
            vm.authorization = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
