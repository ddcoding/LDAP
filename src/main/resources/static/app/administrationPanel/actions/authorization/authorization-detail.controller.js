(function() {
    'use strict';

    angular
        .module('robimytov2App')
        .controller('AuthorizationDetailController', AuthorizationDetailController);

    AuthorizationDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Authorization', 'Role'];

    function AuthorizationDetailController($scope, $rootScope, $stateParams, previousState, entity, Authorization, Role) {
        var vm = this;

        vm.authorization = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('robimytov2App:authorizationUpdate', function(event, result) {
            vm.authorization = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
