
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.systemConfig.marketConfig')
        .controller('MarketConfigPageCtrl', MarketConfigPageCtrl);

    /** @ngInject */
    function MarketConfigPageCtrl($scope, $filter,appBase) {

        $scope.smartTablePageSize = 10;
        $scope.markets = [];
        $scope.update = [];

        $scope.listMarkets=function(){
            appBase.doGet("config/market",null,function(ret){
                 $scope.markets = ret.data;
                 $scope.update = ret.data.slice(0);
            })
        }
        $scope.listMarkets();
        $scope.removeMarket = function(index) {
            $scope.markets.splice(index, 1);
        };
         //**user save
        $scope.insert = [];
        $scope.addMarket = function() {
            $scope.inserted = {
                id: 0,
                name: '',
                url:''
            };
            $scope.markets.push($scope.inserted);
            $scope.insert.push($scope.inserted);
        };
        $scope.saveMarket = function(index){
            var data;
            if(index >= $scope.update.length){
                data = $scope.insert[index-$scope.update.length];
            }else{
                data = $scope.update[index];
            }

            if(data.name == null || data.name.trim()==""){
                appBase.bubMsg("交易市场名称 不能为空");
                $scope.listUser();
                return;
            }
            if(data.url == null || data.url.trim()==""){
                appBase.bubMsg("请求地址 不能为空");
                return;
            }

            appBase.doPost("config/market",data,function(ret){
                appBase.bubMsg("保存成功");
                $scope.listMarkets();
            })
        };
        $scope.cancel = function(){
            $scope.listMarkets();
        }
        $scope.valueChange = function(parent,index){
            //var  name = $(event.target).parent().parent().prev().attr("e-name");
            var  name = parent.$editable.name;
            var  data = parent.$data;
            if(index >= $scope.update.length){
                 switch(name){
                       case "url":
                           $scope.insert[index-$scope.update.length].url =data;
                             break;
                       case "name":
                           $scope.insert[index-$scope.update.length].name =data;
                             break;
                 }
            }else{
                switch(name){
                       case "name":
                           $scope.update[index].url =data;
                             break;
                       case "url":
                           $scope.update[index].url =data;
                             break;
                 }
            }
        };

        $scope.deleteMarket = function(index){
            appBase.doDelete("config/market/"+$scope.users[index].id,null,function(ret){
                appBase.bubMsg("删除成功");
                $scope.listMarkets();
             })
        };

        $scope.cancel = function(){
            $scope.listMarkets();
        }

    }

})();
