package com.ug369.backend.outerapi.controller;

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
//        JsonParser parser =
        return new DataResponse<>(sync);
    }
}
