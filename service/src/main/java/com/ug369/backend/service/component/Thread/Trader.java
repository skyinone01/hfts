package com.ug369.backend.service.component.Thread;

import com.ug369.backend.service.component.Task.TradeTask;
import com.ug369.backend.service.component.market.Yunbi;
import org.bitcoin.market.AbstractMarketApi;
import org.bitcoin.market.MarketApiFactory;
import org.bitcoin.market.bean.AppAccount;
import org.bitcoin.market.bean.Market;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Roy on 2017/8/3.
 */
public class Trader implements Runnable{

    @Autowired
    private Yunbi yunbi;

    private BlockingQueue<TradeTask> taskQueue = new LinkedBlockingDeque<TradeTask>();

    @Override
    public void run() {
        AppAccount appAccount = new AppAccount();

        AbstractMarketApi market = MarketApiFactory.getInstance().getMarket(Market.PeatioCNY);
        while (true){
            TradeTask task = taskQueue.poll();
        }
    }
}