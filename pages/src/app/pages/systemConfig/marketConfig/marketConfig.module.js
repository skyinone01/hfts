
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.systemConfig.marketConfig', [])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {

        $stateProvider.state('config.market', {
            url: '/market',
            templateUrl: 'app/pages/systemConfig/marketConfig/marketConfig.html',
            controller: 'MarketConfigPageCtrl',
            title: '交易市场配置',
            sidebarMeta: {
                order: 100,
            },
        });
    }

})();
