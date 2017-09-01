/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.userResource.roleManager', [])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {

        $stateProvider.state('users.role', {
            url: '/role',
            templateUrl: 'app/pages/systemConfig/tradeConfig/tradeConfig.html',
            controller: 'RoleManagerPageCtrl',
            title: '交易信息配置',
            sidebarMeta: {
                order: 200,
            },
        });
    }

})();
