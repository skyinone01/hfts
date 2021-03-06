/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
	'use strict';
	angular.module('BlurAdmin.pages.dashboard')
		.controller('DashboardDeviceTypeCtrl', DashboardDeviceTypeCtrl);

	/** @ngInject */
	function DashboardDeviceTypeCtrl(baConfig, layoutPaths,$scope,$rootScope,appBase) {
		var layoutColors = baConfig.colors;
		var deviceDatas = [];
		var apiPath = 'http://localhost:8080';
		function getData(bdate,edate) {
			debugger;
			var param={startDate:bdate,endDate:edate};
			appBase.doGet('statistic/module',param, function (response) {
			console.log(response.data);
			response.data.forEach(function (value, index, array) {
				deviceDatas.push({
					country: value.type,
					visits: value.count,
					color: layoutColors.primary
				})
			});
			var map = AmCharts.makeChart('barDeviceChart', {
				type: 'serial',
				theme: 'blur',
				color: layoutColors.defaultText,
				dataProvider: deviceDatas,
				startDuration: 1,
				graphs: [
					{
						balloonText: '<b>[[category]]: [[value]]</b>',
						fillColorsField: 'color',
						fillAlphas: 0.7,
						lineAlpha: 0.2,
						type: 'column',
						valueField: 'visits'
					}
				],
				chartCursor: {
					categoryBalloonEnabled: false,
					cursorAlpha: 0,
					zoomable: false
				},
				categoryField: 'country',
				categoryAxis: {
					gridPosition: 'start',
					labelRotation: 45,
					gridAlpha: 0.5,
					gridColor: layoutColors.border,
				},
				export: {
					enabled: true
				},
				creditsPosition: 'top-right',
				pathToImages: layoutPaths.images.amChart
			});
		});

	}
		function getDataByType(type,bdate,edate) {
			var getPath='statistic';
			if(type==1){
				//getPath=getPath+"/device-count-year"
			}else if(type==2){
				//getPath=getPath+"/device-count-month"
			}else if(type==3){
				//getPath=getPath+"/device-count-day"
			}else{
				getPath=getPath+"/pv-count-year"
			}
			var param={"startDate":bdate,"endDate":edate};
			var datas=[];
			appBase.doGet(getPath,param, function (response) {
				response.data.forEach(function (value, index, array) {
					datas.push({
						country: value.type,
						visits: value.count,
						color: layoutColors.primary
					})
				});
				var map = AmCharts.makeChart('barDeviceChart', {
					type: 'serial',
					theme: 'blur',
					color: layoutColors.defaultText,
					dataProvider: datas,
					startDuration: 1,
					graphs: [
						{
							balloonText: '<b>[[category]]: [[value]]</b>',
							fillColorsField: 'color',
							fillAlphas: 0.7,
							lineAlpha: 0.2,
							type: 'column',
							valueField: 'visits'
						}
					],
					chartCursor: {
						categoryBalloonEnabled: false,
						cursorAlpha: 0,
						zoomable: false
					},
					categoryField: 'country',
					categoryAxis: {
						gridPosition: 'start',
						labelRotation: 45,
						gridAlpha: 0.5,
						gridColor: layoutColors.border,
					},
					export: {
						enabled: true
					},
					creditsPosition: 'top-right',
					pathToImages: layoutPaths.images.amChart
				});
				map.addListener("clickGraphItem", handleClick);

			});
		}

		function handleClick(event)
		{
			$rootScope.isDeviceBackShow=true;
			$rootScope.isDeviceBackHidden=false;
			$scope.$apply();
			var bdate=typeof($rootScope.startDate)!="undefined"?new moment($rootScope.startDate).format('YYYYMMDD'):""
			var edate=typeof($rootScope.endDate)!="undefined"?new moment($rootScope.endDate).format('YYYYMMDD'):""
			//if($scope.DeviceValue==3){
			//	if(bdate!="")bdate=bdate+""+event.item.category.substring(0,2);
			//	if(edate!="")edate=edate+""+event.item.category.substring(0,2);
			//}
			if($scope.DeviceValue==1) {
				var tmpbdate = event.item.category+""+"01";
				var tmpedate = event.item.category+""+"31";
			}
			if($scope.DeviceValue==2) {
				var tmpbdate = event.item.category+""+"000000";
				var tmpedate = event.item.category+""+"235959";
				bdate=bdate+""+"000000";
				edate=edate+""+"235959";
			}
			if($scope.DeviceValue==3) {
				var tmpbdate = event.item.category+""+"0000";
				var tmpedate = event.item.category+""+"5959";
				bdate=bdate+""+"000000";
				edate=edate+""+"235959";
		}
			if(bdate<tmpbdate)bdate=tmpbdate;
			if(edate>tmpedate)edate=tmpedate;
			getData(bdate,edate);
			//alert($scope.DeviceValue);
			//$scope.DeviceValue=2;
			//alert($scope.DeviceValue);

		}

		$scope.$watch('DeviceValue',function () {
			if($scope.DeviceValue!=0) {
				getDataByType($scope.DeviceValue, typeof($rootScope.startDate) != "undefined" ? new moment($rootScope.startDate).format('YYYYMMDD') : "", typeof($rootScope.endDate) != "undefined" ? new moment($rootScope.endDate).format('YYYYMMDD') : "");
			}else{
				getDataByType($rootScope.DeviceType, typeof($rootScope.startDate) != "undefined" ? new moment($rootScope.startDate).format('YYYYMMDD') : "", typeof($rootScope.endDate) != "undefined" ? new moment($rootScope.endDate).format('YYYYMMDD') : "");
			}
		},true);
	}

})();