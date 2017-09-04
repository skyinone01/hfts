
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.userResource.roleManager', [])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {

        $stateProvider.state('users.role', {
            url: '/role',
            templateUrl: 'app/pages/userManager/role/roleManager.html',
            controller: 'RoleManagerPageCtrl',
            title: '角色管理',
            sidebarMeta: {
                order: 200,
            },
        });
    }

})();
