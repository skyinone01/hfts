
(function () {
  'use strict';

  angular.module('BlurAdmin.pages.profile')
    .controller('ProfilePageCtrl', ProfilePageCtrl);

  /** @ngInject */
  function ProfilePageCtrl($scope, fileReader, $filter, appBase) {

    $scope.getUser = function(){
      appBase.doGet("user",null,function(res){
          $scope.user = res.data;
      })
    }
    $scope.getUser();
    $scope.formdata ={
      npassword:"",
      password:""
    };
    $scope.savePassword = function(){
      if($scope.formdata.password == null || $scope.formdata.password.trim() == ""){
          appBase.bubMsg("请输入旧密码");
        return
      }
      if($scope.formdata.npassword == null || $scope.formdata.npassword.trim() == ""){
        appBase.bubMsg("请输入新密码");
        return
      }
      var data = '?password='+$scope.formdata.password +'&npassword='+$scope.formdata.npassword;
      appBase.doPut("user"+data,null,function(){
        if(confirm("修改成功,请重新登录")){
          appBase.goLogin();
        }

      })
    }
    //$scope.picture = $filter('profilePicture')('Nasta');
    //
    //$scope.removePicture = function () {
    //  $scope.picture = $filter('appImage')('theme/no-photo.png');
    //  $scope.noPicture = true;
    //};
    //
    //$scope.uploadPicture = function () {
    //  var fileInput = document.getElementById('uploadFile');
    //  fileInput.click();
    //
    //};
    //
    //$scope.socialProfiles = [
    //  {
    //    name: 'Facebook',
    //    href: 'https://www.facebook.com/akveo/',
    //    icon: 'socicon-facebook'
    //  },
    //  {
    //    name: 'Twitter',
    //    href: 'https://twitter.com/akveo_inc',
    //    icon: 'socicon-twitter'
    //  },
    //  {
    //    name: 'Google',
    //    icon: 'socicon-google'
    //  },
    //  {
    //    name: 'LinkedIn',
    //    href: 'https://www.linkedin.com/company/akveo',
    //    icon: 'socicon-linkedin'
    //  },
    //  {
    //    name: 'GitHub',
    //    href: 'https://github.com/akveo',
    //    icon: 'socicon-github'
    //  },
    //  {
    //    name: 'StackOverflow',
    //    icon: 'socicon-stackoverflow'
    //  },
    //  {
    //    name: 'Dribbble',
    //    icon: 'socicon-dribble'
    //  },
    //  {
    //    name: 'Behance',
    //    icon: 'socicon-behace'
    //  }
    //];
    //
    //$scope.unconnect = function (item) {
    //  item.href = undefined;
    //};
    //
    //$scope.showModal = function (item) {
    //  $uibModal.open({
    //    animation: false,
    //    controller: 'ProfileModalCtrl',
    //    templateUrl: 'app/pages/profile/profileModal.html'
    //  }).result.then(function (link) {
    //      item.href = link;
    //    });
    //};
    //
    //$scope.getFile = function () {
    //  fileReader.readAsDataUrl($scope.file, $scope)
    //      .then(function (result) {
    //        $scope.picture = result;
    //      });
    //};
    //
    //$scope.switches = [true, true, false, true, true, false];
  }

})();
