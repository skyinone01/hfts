package com.ug369.backend.outerapi.controller;


import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ug369.backend.bean.base.request.PageRequest;
import com.ug369.backend.bean.base.response.DataResponse;
import com.ug369.backend.bean.base.response.PagedDataResponse;
import com.ug369.backend.bean.bean.request.BasicRequest;
import com.ug369.backend.bean.result.PagedResult;
import com.ug369.backend.outerapi.annotation.PageDefault;
import com.ug369.backend.service.entity.mysql.Statistics;
import com.ug369.backend.service.entity.mysql.TotalStatistics;
import com.ug369.backend.service.entity.mysql.UserAgeStatistics;
import com.ug369.backend.service.entity.mysql.UserCountStatistics;
import com.ug369.backend.service.entity.mysql.UserDeviceStatistics;
import com.ug369.backend.service.entity.mysql.UserModuleStatistics;
import com.ug369.backend.service.entity.mysql.UserSexStatistics;
import com.ug369.backend.service.repository.rdbsupport.domain.UserImportTemplete;
import com.ug369.backend.service.service.StatisticsService;

import java.io.File;
import java.io.OutputStream;
import java.text.Collator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class StatisticsController {
    @Autowired
    private StatisticsService service;

    /*@RequestMapping()
    public List<Statistics> selectGroup() {
        List<Statistics> countryList = service.selectGroup();
        return countryList;
    }*/

    @RequestMapping(value = "/statistic/total")
    public DataResponse selectTotal() {
        Map result = new HashMap();
        List<TotalStatistics> countryList = service.selectTotal();
        result.put("other", countryList.get(0).getCount());
        result.put("ios", countryList.get(1).getCount());
        result.put("android", countryList.get(2).getCount());
        result.put("newUser", countryList.get(3).getCount());
        result.put("totalUser", countryList.get(4).getCount());
        result.put("sex1", countryList.get(5).getCount());
        result.put("sex2", countryList.get(6).getCount());
        result.put("qq", countryList.get(7).getCount());
        result.put("weChat", countryList.get(8).getCount());
        return new DataResponse(result);
    }

    @RequestMapping("/statistic/age")
    public DataResponse selectAge(String startDate,String endDate) {
        Map result = new HashMap();
        Map dateMap = new HashMap();
        dateMap.put("startDate",startDate);
        dateMap.put("endDate",endDate);
        List<UserAgeStatistics> countryList = service.selectAge(dateMap);
        if(countryList.size()>0){
        result.put("0", countryList.get(0).getCount());
        result.put("1", countryList.get(1).getCount());
        result.put("2", countryList.get(2).getCount());
        result.put("3", countryList.get(3).getCount());
        result.put("4", countryList.get(4).getCount());
        result.put("5", countryList.get(5).getCount());
        result.put("6", countryList.get(6).getCount());
        }else {
            result.put("0", 0);
            result.put("1", 0);
            result.put("2", 0);
            result.put("3", 0);
            result.put("4", 0);
            result.put("5", 0);
            result.put("6", 0);
        }
        return new DataResponse(result);
    }

    @RequestMapping("/statistic/count-year")
    public DataResponse<UserCountStatistics> selectCountYear(String startDate,String endDate) {
        Map dateMap = new HashMap();
        dateMap.put("startDate",startDate);
        dateMap.put("endDate",endDate);
        List<UserCountStatistics> countryList = service.selectCountYear(dateMap);
        return new DataResponse(countryList);
    }

    @RequestMapping("/statistic/count-month")
    public DataResponse<UserCountStatistics> selectCountMonth(String startDate,String endDate) {
        Map dateMap = new HashMap();
        dateMap.put("startDate",startDate);
        dateMap.put("endDate",endDate);
        List<UserCountStatistics> countryList = service.selectCountMonth(dateMap);
        return new DataResponse(countryList);
    }

    @RequestMapping("/statistic/count-day")
    public DataResponse<UserCountStatistics> selectCountDay(String startDate,String endDate) {
        Map dateMap = new HashMap();
        dateMap.put("startDate",startDate);
        dateMap.put("endDate",endDate);
        List<UserCountStatistics> countryList = service.selectCountDay(dateMap);
        return new DataResponse(countryList);
    }
    
    @RequestMapping("/statistic/pv-count-year")
    public DataResponse<UserCountStatistics> selectPvCountYear(String startDate,String endDate) {
        Map dateMap = new HashMap();
        dateMap.put("startDate",startDate);
        dateMap.put("endDate",endDate);
        List<UserCountStatistics> countryList = service.selectPvCountYear(dateMap);
        return new DataResponse(countryList);
    }

    @RequestMapping("/statistic/pv-count-month")
    public DataResponse<UserCountStatistics> selectPvCountMonth(String startDate,String endDate) {
        Map dateMap = new HashMap();
        dateMap.put("startDate",startDate);
        dateMap.put("endDate",endDate);
        List<UserCountStatistics> countryList = service.selectPvCountMonth(dateMap);
        return new DataResponse(countryList);
    }

    @RequestMapping("/statistic/pv-count-day")
    public DataResponse<UserCountStatistics> selectPvCountDay(String startDate,String endDate) {
        Map dateMap = new HashMap();
        dateMap.put("startDate",startDate);
        dateMap.put("endDate",endDate);
        List<UserCountStatistics> countryList = service.selectPvCountDay(dateMap);
        return new DataResponse(countryList);
    }

    @RequestMapping("/statistic/device")
    public DataResponse<UserDeviceStatistics> selectDevice() {
        List<UserDeviceStatistics> countryList = service.selectDevice();
        return new DataResponse(countryList);
    }

    @RequestMapping("/statistic/module")
    public DataResponse<UserModuleStatistics> selectModule() {
        List<UserModuleStatistics> countryList = service.selectModule();
        return new DataResponse(countryList);
    }

    @RequestMapping("/statistic/sex")
    public DataResponse<UserSexStatistics> selectSex() {
        List<UserSexStatistics> countryList = service.selectSex();
        return new DataResponse(countryList);
    }


    @RequestMapping("/statistic/active-user")
    public DataResponse<UserCountStatistics> selectActiveUser() {
        List<UserCountStatistics> countryList = service.selectActiveUser();
        return new DataResponse(countryList);
    }

    @RequestMapping("/statistic/active-module")
    public DataResponse<UserCountStatistics> selectActiveModule() {
        List<UserCountStatistics> countryList = service.selectActiveModule();
        return new DataResponse(countryList);
    }

    @RequestMapping("/statistic/active-device")
    public DataResponse<UserCountStatistics> selectActiveDevice() {
        List<UserCountStatistics> countryList = service.selectActiveDevice();
        return new DataResponse(countryList);
    }

    @RequestMapping("/statistic/uv")
    public DataResponse<UserCountStatistics> selectUv() {
        List<UserCountStatistics> countryList = service.selectUv();
        return new DataResponse(countryList);
    }
    
    
    /**
     * 导出用户综合统计数据
     * @param startDate
     * @param endDate
     * @param request
     * @param response
     * @throws Exception
     */
    private static final SimpleDateFormat exportFormat = new SimpleDateFormat("yyyy-MM-dd");
    @RequestMapping("/statistic/exportComprehensiveUserStats")
    public void exportComprehensiveUserStats(String startDate, String endDate, HttpServletRequest request, HttpServletResponse response) throws Exception{
    	String path = "/data/userComprehensivExportTemplete.xls";
    	//本地测试用/src/main/resources  测试服务用/data 注意服务器“/”的问题
    	//String path = "/src/main/resources/userComprehensivExportTemplete.xls";
    	File templeteFile = new File(System.getProperty("user.dir")+path);
        Workbook workbook = WorkbookFactory.create(templeteFile);

        List<String> statsUserAgeTem = Arrays.asList(UserImportTemplete.STATS_USER_AGE);
        List<String> statsModuleAcc  = Arrays.asList(UserImportTemplete.STATS_MODULE_ACC);

        //用户年龄统计
        int rowNum = 1;
        Row row0 = workbook.getSheetAt(0).createRow(rowNum);
        for(Map.Entry<String, Object> item : service.statsExportUserAge(startDate, endDate).entrySet()){
            if(statsUserAgeTem.contains(item.getKey().toString())){
                row0.createCell(UserImportTemplete.indexOfAge(item.getKey().toString())).setCellValue(item.getValue().toString());
            }
        }
        //新增用户统计
        rowNum = 0;
        List<String> newAddUserTem = new ArrayList<>();
        Row row1 = workbook.getSheetAt(1).createRow(rowNum);
        Map<String, Object> newAddUsers = service.statsExportUserAdd(startDate, endDate);
        for(Map.Entry<String, Object> item : newAddUsers.entrySet()){
            if(!item.getKey().equals("用户总数")){
                newAddUserTem.add(item.getKey().toString());
            }
        }
        Collections.sort(newAddUserTem, Collator.getInstance(Locale.CHINA));
        newAddUserTem.add(0, "用户总数");
        for(String col : newAddUserTem){
            row1.createCell(newAddUserTem.indexOf(col)).setCellValue(col);
        }
        rowNum ++;
        Row row12 = workbook.getSheetAt(1).createRow(rowNum);
        for(Map.Entry<String, Object> item : newAddUsers.entrySet()){
            row12.createCell(newAddUserTem.indexOf(item.getKey().toString())).setCellValue(item.getValue().toString());
        }

        //模块访问统计
        rowNum = 1;
        Row row2 = workbook.getSheetAt(2).createRow(rowNum);
        for(Map.Entry<String, Object> item : service.statsExportModuleAcc(startDate, endDate).entrySet()){
            if(statsModuleAcc.contains(item.getKey().toString())){
                row2.createCell(UserImportTemplete.indexOfModuleAcc(item.getKey().toString())).setCellValue(item.getValue().toString());
            }
        }

        //终端分布统计
        rowNum = 0;
        List<String> terminalRangeTem = new ArrayList<>();
        Row row3 = workbook.getSheetAt(3).createRow(rowNum);
        Map<String, Object> terminalRangeSts = service.statsExportTerminalRang(startDate, endDate);
        for(Map.Entry<String, Object> item : terminalRangeSts.entrySet()){
            if(!item.getKey().equals("用户总数")){
                terminalRangeTem.add(item.getKey().toString());
            }
        }
        Collections.sort(terminalRangeTem, Collator.getInstance(Locale.CHINA));
        terminalRangeTem.add(0, "用户总数");
        for(String col : terminalRangeTem){
            row3.createCell(terminalRangeTem.indexOf(col)).setCellValue(col);
        }
        rowNum ++;
        Row row32 = workbook.getSheetAt(3).createRow(rowNum);
        for(Map.Entry<String, Object> item : service.statsExportTerminalRang(startDate, endDate).entrySet()){
            row32.createCell(terminalRangeTem.indexOf(item.getKey().toString())).setCellValue(item.getValue().toString());
        }

        response.setHeader("Content-Disposition", "attachment;filename="+new String("用户综合统计数据.xls".getBytes(), "iso8859-1"));
        response.setCharacterEncoding("utf-8");
        OutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
    }
    
    @RequestMapping(value = "/statistic/activeUser", method = RequestMethod.GET)
    public PagedDataResponse<BasicRequest> activeUserList(@PageDefault PageRequest pageRequest) {
        PagedResult<UserCountStatistics> users = service.getActiveUserList(pageRequest);
        return PagedDataResponse.of(users);
    }
    
    @RequestMapping(value = "/statistic/activeModule", method = RequestMethod.GET)
    public PagedDataResponse<BasicRequest> activeModuleList(@PageDefault PageRequest pageRequest) {
        PagedResult<UserCountStatistics> module = service.getActiveModuleList(pageRequest);
        return PagedDataResponse.of(module);
    }
    
    @RequestMapping(value = "/statistic/activeDevice", method = RequestMethod.GET)
    public PagedDataResponse<BasicRequest> activeDeviceList(@PageDefault PageRequest pageRequest) {
        PagedResult<UserCountStatistics> device = service.getActiveDeviceList(pageRequest);
        return PagedDataResponse.of(device);
    }

}
