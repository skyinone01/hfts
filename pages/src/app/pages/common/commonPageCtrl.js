
(function () {
  'use strict';

  angular.module('BlurAdmin.pages.common')
    .controller('infoModalCtrl', infoModalCtrl);

  /** @ngInject */
  function infoModalCtrl($scope, $uibModal, content) {
    $scope.content = content;
    $scope.title = "提示";

  }

})();
