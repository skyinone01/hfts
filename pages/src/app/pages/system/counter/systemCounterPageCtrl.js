/**
 * @author Roy
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.system.monitor')
        .controller('SystemCounterPageCtrl', SystemCounterPageCtrl);

    /** @ngInject */
    function SystemCounterPageCtrl($scope, appBase, $interval) {

        $scope.markets=[{'value':0,'text':'Yunbi'},{'value':1,'text':'Okcoin'},{'value':1,'text':'BTER'},{'value':1,'text':'BTCTRADE'}]

        $scope.getMarkets = function () {
            appBase.doGet("markets",null,function(res){
                $scope.buys = res.data.buys;
                $scope.sells = res.data.sells;
            })

            $interval(function(){
                appBase.doGet("markets",null,function(res){
                    $scope.buys = res.data.buys;
                    $scope.sells = res.data.sells;
                })
            },10000)
        }

        $scope.selectMarket = function(){
            alert("select");
        }

        $scope.getMarkets();
        $scope.currentPrice = 29635.23

    }
})();