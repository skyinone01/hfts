
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.system.monitor', [])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {

        $stateProvider.state('system.monitor', {
            url: '/monitor',
            templateUrl: 'app/pages/system/counter/systemCounter.html',
            controller: 'SystemCounterPageCtrl',
            title: '用户交易面板',
            sidebarMeta: {
                order: 10,
            },
        });
    }

})();
