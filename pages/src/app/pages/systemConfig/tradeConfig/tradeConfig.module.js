
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.systemConfig.tradeConfig', [])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider) {

        $stateProvider.state('config.trade', {
            url: '/trade',
            templateUrl: 'app/pages/systemConfig/tradeConfig/tradeConfig.html',
            controller: 'TradeConfigPageCtrl',
            title: '交易账户配置',
            sidebarMeta: {
                order: 200,
            },
        });
    }

})();
