package com.ug369.backend.outerapi.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ug369.backend.bean.base.response.DataResponse;
import com.ug369.backend.bean.exception.UgmsStatus;
import com.ug369.backend.bean.exception.UserException;
import com.ug369.backend.service.component.HTTP.HTTPSendor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Roy on 2017/8/23.
 */
@RestController
public class UserPanelController {

    @Autowired
    private ObjectMapper objectMapper;

    private static Logger logger = LoggerFactory.getLogger(UserPanelController.class);

    @RequestMapping(value = "/markets", method = RequestMethod.GET)
    public DataResponse getMarket() {
        String sync;
        try {
            sync = HTTPSendor.getSync("https://www.btctrade.com/coin/rmb/btc/order.js", null).body().string();
        } catch (IOException e) {
            logger.error("markets error", e);
            throw new UserException(UgmsStatus.SYS_ERROR, "获取交易数据失败，稍后再试");
        }
        JSONObject object = (JSONObject) JSONObject.parse(sync);
        JSONArray items = object.getJSONArray("d");
        List buys = new ArrayList<>();
        List sells = new ArrayList<>();
        if (items!=null&&items.size()>0){
            items.forEach(o->{
                if(((JSONObject)o).get("s").equals("buy")){
                    if (buys.size() <15){
                        buys.add(o);
                    }
                }else if(((JSONObject)o).get("s").equals("sell")){
                    if (sells.size() <15){
                        sells.add(o);
                    }
                }
            });
        }else {
            logger.error("markets size is 0");
            throw new UserException(UgmsStatus.SYS_ERROR, "获取交易数据失败，稍后再试");
        }

        Map ret =new HashMap<>();
        ret.put("buys",buys);
        ret.put("sells",sells);

        return new DataResponse<>(ret);
    }
}
