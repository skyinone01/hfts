/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.userResource.userManager', [])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {

        $stateProvider.state('users.user', {
            url: '/user',
            templateUrl: 'app/pages/userManager/user/userManager.html',
            controller: 'UserManagerPageCtrl',
            title: '交易市场配置',
            sidebarMeta: {
                order: 100,
            },
        });
    }

})();
