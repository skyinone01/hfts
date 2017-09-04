
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.systemConfig.tradeConfig')
        .controller('TradeConfigPageCtrl', TradeConfigPageCtrl);

    /** @ngInject */
    function TradeConfigPageCtrl($scope, $filter,appBase) {

        $scope.smartTablePageSize = 10;
        $scope.trades = [];
        $scope.update = [];
        $scope.markets = [];

        $scope.listItems=function(){
            appBase.doGet("config/trade",null,function(ret){
                $scope.trades = ret.data;
                $scope.update = ret.data.slice(0);
            })
        }

        $scope.listMarkets=function(){
            appBase.doGet("config/market",null,function(ret){
                $scope.markets = ret.data;
                $scope.listItems();
            })
        }
        $scope.listMarkets();


        $scope.removeItem = function(index) {
            $scope.trades.splice(index, 1);
        };

        $scope.showOne = function(item) {
            if(item.platform && $scope.markets.length) {
                var selected = $filter('filter')($scope.markets, {id: item.platform});
                return selected.length ? selected[0].name : '未设置';
            } else return '未设置'
        };
        //**user save
        $scope.insert = [];
        $scope.addItem = function() {
            $scope.inserted = {
                id: 0,
                accesskey: '',
                secretkey: '',
                platform: 0
            };
            $scope.trades.push($scope.inserted);
            $scope.insert.push($scope.inserted);
        };
        $scope.saveItem = function(index){
            var data;
            if(index >= $scope.update.length){
                data = $scope.insert[index-$scope.update.length];
            }else{
                data = $scope.update[index];
            }

            if(data.accesskey == null || data.accesskey.trim()==""){
                appBase.bubMsg("accessKey 不能为空");
                //$scope.listItems();
                return;
            }
            if(data.secretkey == null || data.secretkey.trim()==""){
                appBase.bubMsg("secretKey 不能为空");
                return;
            }
            if(data.platform == 0 ){
                appBase.bubMsg("platform 不能为空");
                return;
            }

            appBase.doPost("config/trade",data,function(ret){
                appBase.bubMsg("保存成功");
                $scope.listItems();
            })
        };
        $scope.cancel = function(){
            $scope.listItems();
        }
        $scope.valueChange = function(parent,index){
            //var  name = $(event.target).parent().parent().prev().attr("e-name");
            var  name = parent.$editable.name;
            var  data = parent.$data;
            if(index >= $scope.update.length){
                switch(name){
                    case "accessKey":
                        $scope.insert[index-$scope.update.length].accesskey =data;
                        break;
                    case "secretKey":
                        $scope.insert[index-$scope.update.length].secretkey =data;
                        break;
                    case "platform":
                        $scope.insert[index-$scope.update.length].platform =data;
                        break;
                }
            }else{
                switch(name){
                    case "secretKey":
                        $scope.update[index].secretkey =data;
                        break;
                    case "accessKey":
                        $scope.update[index].accesskey =data;
                        break;
                    case "platform":
                        $scope.update[index].platform =data;
                        break;
                }
            }
        };

        $scope.deleteItem = function(index){
            appBase.doDelete("config/trade/"+$scope.users[index].id,null,function(ret){
                appBase.bubMsg("删除成功");
                $scope.listItems();
            })
        };
    }

})();
