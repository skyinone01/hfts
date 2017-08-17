/**
 * @author Roy
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.system.monitor')
        .controller('SystemCounterPageCtrl', SystemCounterPageCtrl);

    /** @ngInject */
    function SystemCounterPageCtrl($scope, appBase, toastr, toastrConfig) {
        $scope.markets=[{'value':0,'text':'Yunbi'},{'value':1,'text':'Okcoin'},{'value':1,'text':'BTER'},{'value':1,'text':'BTCTRADE'}]
        $scope.buys=[{'price':1,'volume':'0.369'},{'price':1,'volume':'7.39'},
                        {'price':1,'volume':'5.9'},{'price':1,'volume':'0.3693'},
                        {'price':1,'volume':'5.9'},{'price':1,'volume':'0.3693'},
                        {'price':1,'volume':'5.9'},{'price':1,'volume':'0.3693'},
                        {'price':1,'volume':'5.9'},{'price':1,'volume':'0.3693'},
                        {'price':1,'volume':'5.9'},{'price':1,'volume':'0.3693'}]
        $scope.sells=[{'price':1,'volume':'0.369'},{'price':1,'volume':'7.39'},
            {'price':1,'volume':'5.9'},{'price':1,'volume':'0.3693'},
            {'price':1,'volume':'5.9'},{'price':1,'volume':'0.3693'},
            {'price':1,'volume':'5.9'},{'price':1,'volume':'0.3693'},
            {'price':1,'volume':'5.9'},{'price':1,'volume':'0.3693'},
            {'price':1,'volume':'5.9'},{'price':1,'volume':'0.3693'}]


        //appBase.doGet("monitor",null,function(res){
        //
        //})

        $scope.selectMarket = function(){
            alert("select");
        }

        $scope.currentPrice = 29635.23

    }
})();