/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.userResource.userManager')
        .controller('UserManagerPageCtrl', UserManagerPageCtrl);

    /** @ngInject */
    function UserManagerPageCtrl($scope, $filter,appBase) {

        $scope.smartTablePageSize = 10;
        $scope.users = [];
        $scope.role = [];
        $scope.update = [];

        $scope.listUser=function(){
            appBase.doGet("users",null,function(ret){
                 $scope.users = ret.data.items;
                 $scope.update = ret.data.items;
            })
        }
        $scope.listRole=function(){
              appBase.doGet("roles",null,function(ret){
                  $scope.role = ret.data;
               })
         }
        $scope.listRole();
        $scope.listUser();


        $scope.showStatus = function(user) {
            var selected = [];
            if(user.role) {
                selected = $filter('filter')($scope.role, {value: user.role});
            }
            return selected.length ? selected[0].text : '未设置';
        };

        $scope.removeUser = function(index) {
            $scope.users.splice(index, 1);
        };
         //**user save
        $scope.insert = [];
        $scope.addUser = function() {
            $scope.inserted = {
                id: 0,
                username: '',
                name:'',
                mobile: '',
                role: null
            };
            $scope.users.push($scope.inserted);
            $scope.insert.push($scope.inserted);
        };
        $scope.saveUser = function(index){
            var data;
            if(index >= $scope.users.length){
                data = $scope.insert[index];
            }else{
                data = $scope.update[index];
            }
            appBase.doPost("users",data,function(ret){
                appBase.bubMsg("保存成功");
                $scope.listUser();
            })
        };
        $scope.change = function(obj,data){
            alert(obj);
        };
        $scope.beforeChange;
        $scope.change = function(data,index){
            var selected = $filter('filter')($scope.role, {value: data});
            if(selected.length){
                if(index >= $scope.update.length){
                     $scope.insert[index].role =data;
                }else{
                    $scope.update[index].name =data;
                }
            }
        }
        $scope.valueChange = function(event,index){
            var  name = $(event.target).parent().parent().prev().attr("e-name");
//            var name;
//            if(selected.length){
//                name="role";
//            }else {
//                name = $(event.target).parent().parent().prev().attr("e-name");
////                var key = $(event.target).parent().parent().prev().attr("e-name");
//                if( data != 'undefined' && data !=""){
//                    $scope.beforeChange = data;
//                }else{
//                    $scope.beforeChange ="";
//                }
//            }

            if(index >= $scope.update.length){
                 switch(name){
                       case "username":
                           $scope.insert[index].username =event.target.value;
                             break;
                       case "name":
                           $scope.insert[index].name =event.target.value;
                             break;
                       case "mobile":
                           $scope.insert[index].mobile =event.target.value;
                             break;
                       case "role":
                           $scope.insert[index].role =event.target.value;
                             break;
                 }
            }else{
                switch(name){
                       case "username":
                           $scope.update[index].username =event.target.value;
                             break;
                       case "role":
                           $scope.update[index].role =event.target.value;
                             break;
                       case "mobile":
                           $scope.update[index].mobile =event.target.value;
                           break;
                       case "name":
                           $scope.update[index].name =event.target.value;
                           break;
                 }
            }
        };

        $scope.cancelSave = function(index){

        };

        $scope.showRole = function(user) {
            if(user.role && $scope.role.length) {
                var selected = $filter('filter')($scope.role, {value: user.role});
                return selected.length ? selected[0].text : '未设置';
            } else return '未设置'
        };

        $scope.deleteUser = function(index){
            appBase.doDelete("users/"+$scope.users[index].id,null,function(ret){
                appBase.bubMsg("删除成功");
                $scope.removeUser(index);
             })
        };

    }

})();
