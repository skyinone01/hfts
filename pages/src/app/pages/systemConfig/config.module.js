
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.systemConfig', [

       'BlurAdmin.pages.systemConfig.marketConfig',
       'BlurAdmin.pages.systemConfig.tradeConfig',

    ])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('config', {
                url: '/config',
                template : '<ui-view  autoscroll="true" autoscroll-body-top></ui-view>',
                abstract: true,
                title: '系统配置管理',
                sidebarMeta: {
                    icon: 'ion-grid',
                    order: 40,
                },
            });

        $urlRouterProvider.when('/config','/config/trade');
    }

})();
