package com.ug369.backend.service.component.market;

import org.bitcoin.market.AbstractMarketApi;
import org.bitcoin.market.MarketApiFactory;
import org.bitcoin.market.bean.Market;

/**
 * Created by Roy on 2017/8/7.
 */
public class Yunbi {

    AbstractMarketApi market = MarketApiFactory.getInstance().getMarket(Market.PeatioCNY);


}
