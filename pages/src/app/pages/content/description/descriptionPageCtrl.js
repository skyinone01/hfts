/**
 * @author roy
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.content.description')
        .controller('DescriptionPageCtrl', DescriptionPageCtrl);

    /** @ngInject */
    function DescriptionPageCtrl($scope,$uibModal, baProgressModal, $filter,appBase) {

        $scope.perPage = 20;
        $scope.page = 1;
        $scope.listItem = function(){
                appBase.doGet("description?page="+$scope.page+"&perPage="+$scope.perPage,null,function(response){
        		     if(response.data != null){
        		         $scope.items = response.data.items;
        		         $scope.totalPage = Math.ceil(response.data.total_count/$scope.perPage);
        		     }
        		})
        }
		$scope.listItem();

		$scope.showButton = function(index,name){
			if(name == 'edit'){
				var s = $scope.items[index].status;
				if(s==4){
					return false;
				}
			}
			if(name == 'delete'){
				var s = $scope.items[index].status;
				if(s==4 ){
					return false;
				}
			}
			if(name == 'operate'){
				var s = $scope.items[index].status;
				if(s==3){
					return false;
				}
			}
			return true;
		}

		$scope.deleteOne = function(id){
			var result = confirm('确认删除！');
			if(!result){
				return;
			}
            appBase.doDelete("description/"+id,null,function(res){
                appBase.bubMsg("删除成功");
				$scope.listItem();
            });
		}

		//op 1 新增 2详情 3编辑 4 审核
		$scope.open = function (page, size, id,opstr,type) {
			$uibModal.open({
				animation: true,
				templateUrl: page,
				size: size,
				controller: 'descriptionModalCtrl',
				resolve: {
					modelId: id,
					op: opstr,
					type: type,
				}
			});
		};
		$scope.openProgressDialog = baProgressModal.open;

		$scope.statusEmu = ["待审核","审批通过","审批未通过","已发布","已作废"];
		$scope.showStatus = function(status){
			return $scope.statusEmu[status-1];
		}

		$scope.showType = function(type){
			if(type=="attention"){
				return "注意事项";
			}else{
				return "说明页";
			}
		}
		$scope.showUseable=function(use){
			if(!use){
				return "否";
			}
			return "是";
		}

		//1待审批、2审批通过、3审批不通过、4已发布、5已作作废】
		$scope.showButtonName = function(index){
			switch($scope.items[index].status){
				case 1:
					return "审核";
				case 2:
					return "发布";
				case 3:
					return "审核";
				case 4:
					return "停用";
				case 5:
					return "启用";
			}
		}
		//审核 1 发布 2 启用 3 停用 4
		//1待审批、2审批通过、3审批不通过、4已发布、5已作作废】
		$scope.operation = function(name,id){
			var op=0;
			switch(name){
				case "审核":
					$scope.open('app/pages/content/description/descriptionModal.html', 'lg',id,4)
					return;
				case "发布":
					op=4;
					break;
				case "启用":
					op=4;
					break;
				case "停用":
					op=5;
					break;
			}

			appBase.doPut("description/"+id+"?op="+op,null,function(response){
				appBase.bubMsg(name+"成功");
				$scope.listItem();
			});
		}

        $scope.upAble = function(page){
            if(page == 1){
                return true;
            }
            return false;
        }

        $scope.nextAble = function(page){
            if(page == $scope.totalPage){
                return true;
            }
            return false;
        }

		$scope.btnNext = function(){
		    $scope.page = $scope.page+1;
		    $scope.listItem();
		};

		$scope.btnUp = function () {
			$scope.page = $scope.page-1;
            $scope.listItem();
		};

    }

})();
