package com.ug369.backend.service.service;

import com.ug369.backend.bean.base.request.WebUser;
import com.ug369.backend.bean.bean.response.PlatformResponse;
import com.ug369.backend.bean.bean.response.TradeResponse;
import com.ug369.backend.service.entity.mysql.Platform;
import com.ug369.backend.service.entity.mysql.TradeConfig;
import com.ug369.backend.service.repository.mysql.PlatformRepository;
import com.ug369.backend.service.repository.mysql.TradeConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemConfigService {
    @Autowired
    private PlatformRepository platformRepository;


    @Autowired
    private TradeConfigRepository tradeConfigRepository;

    public List<PlatformResponse> getMarkets(){
        List<PlatformResponse> data = platformRepository.getData("Config.getMarkets", null);
        return data;
    }
    public void deleteMarket(long pid){
       platformRepository.getData("Config.deleteMarket", pid);
    }
    public void addMarket(PlatformResponse platform){
        Platform o  = new Platform();
        if (platform.getId() != 0){
            o.setId(platform.getId());
        }
        o.setName(platform.getName());
        o.setUrl(platform.getUrl());
        platformRepository.save(o);
    }

    public List<TradeResponse> getTrades(WebUser user) {
        List<TradeResponse> data = platformRepository.getData("Config.getTrades", user.getId());
        return data;
    }

    public void addTrade(TradeResponse trade, WebUser user) {
        TradeConfig o = new TradeConfig();
        if (trade.getId() != 0){
            o.setId(trade.getId());
        }
        o.setAccessKey(trade.getAccessKey());
        o.setSecretKey(trade.getSecretKey());
        o.setPlatform(trade.getPlatform());
        o.setUser(user.getId());
        tradeConfigRepository.save(o);
    }

    public void deleteTrade(long id) {

    }
}
