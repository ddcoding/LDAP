(function() {
    'use strict';

    angular
        .module('ldapApp', ['ui.router','ngResource']).run(run);

    run.$inject = ['stateHandler'];
function run(stateHandler) {
    stateHandler.initialize();
}
})();