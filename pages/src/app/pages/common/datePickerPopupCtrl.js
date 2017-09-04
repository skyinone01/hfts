
(function(){
    'use strict';

    angular.module('BlurAdmin.pages.common')
        .controller('datePickerPopupCtrl', datePickerPopupCtrl);

    /** @ngInject */
    function datePickerPopupCtrl($scope) {

        $scope.open = open;
        $scope.opened = false;
        $scope.formats = ['dd-MMMM-yyyy', 'yyyy-MM-dd hh:mm:ss', 'dd.MM.yyyy', 'shortDate'];
        $scope.format = $scope.formats[1];
        $scope.options = {
            showWeeks: false
        };

        function open() {
            $scope.opened = true;
        }
    }
})();