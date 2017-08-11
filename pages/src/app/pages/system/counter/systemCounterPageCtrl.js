/**
 * @author Roy
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.system.monitor')
        .controller('SystemCounterPageCtrl', SystemCounterPageCtrl);

    /** @ngInject */
    function SystemCounterPageCtrl($scope,baConfig, layoutPaths, appBase,baUtil) {

        var chart =$scope.paintDada = function(data,id){
            AmCharts.makeChart(id, {
                "type": "serial",
                "theme": "light",
                "marginRight": 80,
                "dataProvider": data,
                "valueAxes": [{
                    "position": "left",
                    "title": id
                }],
                "graphs": [{
                    "id": "g1",
                    "fillAlphas": 0.4,
                    "valueField": "useage"
                    //"balloonText": "<div style='margin:5px; font-size:19px;'>Visits:<b>[[value]]</b></div>"
                }],
                "chartCursor": {
                    "categoryBalloonDateFormat": "JJ:NN, DD MMMM",
                    "cursorPosition": "mouse"
                },
                "categoryField": "datepoint",
                "categoryAxis": {
                    "minPeriod": "mm",
                    "parseDates": true
                },
                "export": {
                    "enabled": true,
                    "dateFormat": "yyyy-MM-dd hh:mm:ss"
                }
            });

        }

        appBase.doGet("monitor",null,function(res){

            if(res!=null && res.data!=null && res.data.cpu.length > 0){
                $scope.paintDada(res.data.cpu,"cpu");
                $scope.paintDada(res.data.memory,"memory");
                $scope.paintDada(res.data.disk,"storage");
                $scope.paintDada(res.data.network,"network");

                $scope.tmemory = res.data.memory[0].total;
                $scope.tstorage = res.data.disk[0].total;
                $scope.tnetwork = res.data.network[0].total;
            }else {
                appBase.bubMsg("没有相应数据")
            }
        })


        $scope.setDate = function(){
            $scope.realDate = $("#data_id").val();
            appBase.doGet("monitor?date="+$("#data_id").val(),null,function(res){
                if(res!=null && res.data!=null &&res.data.cpu.length > 0){
                    $scope.paintDada(res.data.cpu,"cpu");
                    $scope.paintDada(res.data.memory,"memory");
                    $scope.paintDada(res.data.disk,"storage");
                    $scope.paintDada(res.data.network,"network");

                    $scope.tmemory = res.data.memory[0].total;
                    $scope.tstorage = res.data.disk[0].total;
                    $scope.tnetwork = res.data.network[0].total;
                }else {
                    appBase.bubMsg("没有相应数据")
                }

            })
        }


    }
})();