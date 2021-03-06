package com.ug369.backend.outerapi.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ug369.backend.bean.base.request.WebUser;
import com.ug369.backend.bean.base.response.BasicResponse;
import com.ug369.backend.bean.base.response.DataResponse;
import com.ug369.backend.bean.exception.UgmsStatus;
import com.ug369.backend.bean.exception.UserException;
import com.ug369.backend.outerapi.annotation.UserInjected;
import com.ug369.backend.service.component.Bean.TradePolicy;
import com.ug369.backend.service.component.HTTP.HTTPSendor;
import com.ug369.backend.service.service.PlatformService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class UserPanelController {

    @Autowired
    private PlatformService platformService;

    private static Logger logger = LoggerFactory.getLogger(UserPanelController.class);

    @RequestMapping(value = "/markets", method = RequestMethod.GET)
    public DataResponse getMarket() {

        System.out.println(Instant.now().getEpochSecond());
        String markets;
        String rates;
        try {
            markets = HTTPSendor.getSync("https://www.btctrade.com/coin/rmb/btc/order.js", null).body().string();
            rates = HTTPSendor.getSync("https://www.btctrade.com/coin/rmb/rate.js", null).body().string();
        } catch (IOException e) {
            logger.error("markets error", e);
            throw new UserException(UgmsStatus.SYS_ERROR, "获取交易数据失败，稍后再试");
        }
        JSONObject marketsObj = (JSONObject) JSONObject.parse(markets);
        JSONObject ratesObj = (JSONObject) JSONObject.parse(rates);
        JSONArray items = marketsObj.getJSONArray("d");
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
        ret.put("currentPrice",ratesObj.get("btc"));

        return new DataResponse<>(ret);
    }

    @RequestMapping(value = "/platforms", method = RequestMethod.GET)
    public DataResponse  getPlatform(){

        return new DataResponse(platformService.getPlatforms());

    }

    @RequestMapping(value = "/policy", method = RequestMethod.GET)
    public DataResponse  policy(@UserInjected WebUser user){

        return new DataResponse(platformService.getPolicy(user));

    }
    @RequestMapping(value = "/policy", method = RequestMethod.POST)
    public BasicResponse addPolicy(TradePolicy tradePolicy){

        platformService.addOrUpdatePolicy(tradePolicy);
        return BasicResponse.success();

    }

}
