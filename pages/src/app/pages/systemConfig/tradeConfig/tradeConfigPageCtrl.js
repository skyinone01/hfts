
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.systemConfig.tradeConfig')
        .controller('TradeConfigPageCtrl', TradeConfigPageCtrl);

    /** @ngInject */
    function TradeConfigPageCtrl($scope, $filter,appBase) {

        $scope.smartTablePageSize = 10;
        $scope.trades = [];
        $scope.update = [];

        $scope.listItems=function(){
            appBase.doGet("config/trade",null,function(ret){
                $scope.trades = ret.data;
                $scope.update = ret.data.slice(0);
            })
        }
        $scope.listItems();
        $scope.removeItem = function(index) {
            $scope.trades.splice(index, 1);
        };

        $scope.showOne = function(item) {
            if(item.platform && $scope.markets.length) {
                var selected = $filter('filter')($scope.markets, {value: item.platform});
                return selected.length ? selected[0].text : '未设置';
            } else return '未设置'
        };
        //**user save
        $scope.insert = [];
        $scope.addItem = function() {
            $scope.inserted = {
                id: 0,
                accessKey: '',
                secretKey: '',
                platform: 0,
                url:''
            };
            $scope.markets.push($scope.inserted);
            $scope.insert.push($scope.inserted);
        };
        $scope.saveItem = function(index){
            var data;
            if(index >= $scope.update.length){
                data = $scope.insert[index-$scope.update.length];
            }else{
                data = $scope.update[index];
            }

            if(data.accessKey == null || data.accessKey.trim()==""){
                appBase.bubMsg("accessKey 不能为空");
                //$scope.listItems();
                return;
            }
            if(data.secretKey == null || data.secretKey.trim()==""){
                appBase.bubMsg("secretKey 不能为空");
                return;
            }
            if(data.platform == null || data.platform.trim()==""){
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
                        $scope.insert[index-$scope.update.length].accessKey =data;
                        break;
                    case "secretKey":
                        $scope.insert[index-$scope.update.length].secretKey =data;
                        break;
                    case "platform":
                        $scope.insert[index-$scope.update.length].platform =data;
                        break;
                }
            }else{
                switch(name){
                    case "secretKey":
                        $scope.update[index].secretKey =data;
                        break;
                    case "accessKey":
                        $scope.update[index].accessKey =data;
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
