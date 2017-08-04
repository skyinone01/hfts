/**
 * @author roy
 * created on 05.04.2017
 */
(function () {
	'use strict';

	angular.module('BlurAdmin.pages.article.label')
		.controller('LabelPageCtrl', LabelPageCtrl);

	/** @ngInject */
	function LabelPageCtrl($scope,$filter,appBase) {

		$scope.types = [];
		$scope.role = [];
		$scope.update = [];

		$scope.perPage =20;
		$scope.page = 1;
		$scope.listType = function(searchValue){
			appBase.doGet("articleLabel?page="+$scope.page+"&perPage="+$scope.perPage+"&searchValue="+searchValue,null,function(response){
				if(response.data != null){
					$scope.types = response.data.items;
					$scope.update = response.data.items.slice(0);
					$scope.totalPage = Math.ceil(response.data.total_count/$scope.perPage);
				}
			})
		}
		$scope.searchValue={
			value:"",
			level:0
		};
		$scope.listType($scope.searchValue.value);

		$scope.search = function () {
			$scope.listType($scope.searchValue.value);
		}

		$scope.filterLevel = function () {
			if($scope.searchValue.level.value==0){
				$scope.types = $scope.update;
			}else {
				$scope.types = $filter('filter')($scope.update, {level: $scope.searchValue.level.value});
			}

		}

		$scope.level=[{'value':1,'text':'一级'},{'value':2,'text':'二级'},{'value':3,'text':'三级'}]
		$scope.levelSearch=[{'value':0,'text':'全部'},{'value':1,'text':'一级'},{'value':2,'text':'二级'},{'value':3,'text':'三级'}]
		//$scope.listLevel=function(){
		//	appBase.doGet("/articleLevel/list",null,function(ret){
		//		$scope.level = ret.data;
		//	})
		//}
		//$scope.listLevel();

		$scope.cancel = function(){
			$scope.listType($scope.searchValue.value);
		}

		$scope.showLevel = function(type) {
			if(type.level && $scope.level.length) {
				var selected = $filter('filter')($scope.level, {value: type.level});
				return selected.length ? selected[0].text : '未设置';
			} else return '未设置'
		};

		$scope.removeType = function(index) {
			$scope.types.splice(index, 1);
		};
		//**user save
		$scope.insert = [];
		$scope.addType = function() {
			$scope.inserted = {
				id: 0,
				name: '',
				level:0
			};
			$scope.types.push($scope.inserted);
			$scope.insert.push($scope.inserted);
		};
		$scope.saveType = function(index){
			var data;
			if(index >= $scope.update.length){
				data = $scope.insert[index-$scope.update.length];
			}else{
				data = $scope.update[index];
			}
			if (data.name == "" || data.name == null){
				appBase.bubMsg("名称不能为空");
				return;
			}
			if (data.level == null || data.level == 0){
				appBase.bubMsg("级别不能为空");
				return;
			}
			appBase.doPut("articleLabel",data,function(ret){
				appBase.bubMsg("保存成功");
				$scope.listType();
			})
		};

		$scope.valueChange = function(parent,index){
			//var  name = $(event.target).parent().parent().prev().attr("e-name");
			var  name = parent.$editable.name;
			var  data = parent.$data;
			if(index >= $scope.update.length){
				switch(name){
					case "name":
						$scope.insert[index-$scope.update.length].name =data;
						break;
					case "level":
						$scope.insert[index-$scope.update.length].level =data;
						break;
				}
			}else{
				switch(name){
					case "name":
						$scope.update[index].name =data;
						break;
					case "level":
						$scope.update[index].level =data;
						break;
				}
			}
		};

		$scope.cancelSave = function(index){

		};
		$scope.deleteType = function(index){
			appBase.doDelete("articleLevel/"+$scope.types[index].id,null,function(ret){
				appBase.bubMsg("删除成功");
				$scope.listType();
				//$scope.removeType(index);
			})
		};


		$scope.upAble = function(page){
			if(page == 1){
				return true;
			}
			return false;
		}

		$scope.nextAble = function(page){
			if($scope.totalPage==0){
				return true;
			}
			if(page == $scope.totalPage){
				return true;
			}
			return false;
		}

		$scope.btnNext = function(){
			$scope.page = $scope.page+1;
			$scope.listType($scope.searchValue.value);
		};

		$scope.btnUp = function () {
			$scope.page = $scope.page-1;
			$scope.listType($scope.searchValue.value);
		};

	}

})();