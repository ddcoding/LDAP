(function() {
    'use strict';

    angular
        .module('ldapApp', []).run(run);

    run.$inject = ['stateHandler'];

    function run(stateHandler) {
        stateHandler.initialize();
    }
})();