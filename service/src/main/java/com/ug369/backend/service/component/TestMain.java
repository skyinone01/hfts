package com.ug369.backend.service.component;

import com.ug369.backend.service.component.Bean.YunbiConfig;
import org.bitcoin.market.AbstractMarketApi;
import org.bitcoin.market.MarketApiFactory;
import org.bitcoin.market.bean.BitOrder;
import org.bitcoin.market.bean.Market;

import java.io.IOException;
import java.util.List;

/**
 * Created by Roy on 2017/8/23.
 */
public class TestMain {

    public static void  main(String[] args) throws IOException {

        AbstractMarketApi market = MarketApiFactory.getInstance().getMarket(Market.PeatioCNY);
        List<BitOrder> runningOrders = market.getRunningOrders(YunbiConfig.getAppAccount());


        System.out.println(runningOrders);




//        String sync = HTTPSendor.getSync("https://www.btctrade.com/coin/rmb/btc/order.js", null).body().string();

//        HTTPSendor.getSync("https://www.btctrade.com/coin/rmb/btc/order.js", null).body().string();

//        ResponseBody body = sync.body();

//        System.out.println(sync);

    }
}
