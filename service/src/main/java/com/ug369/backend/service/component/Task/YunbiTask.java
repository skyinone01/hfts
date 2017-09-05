package com.ug369.backend.service.component.Task;

import com.ug369.backend.service.component.Bean.YunbiConfig;
import org.bitcoin.market.AbstractMarketApi;
import org.bitcoin.market.MarketApiFactory;
import org.bitcoin.market.bean.AppAccount;
import org.bitcoin.market.bean.Market;
import org.bitcoin.market.bean.SymbolPair;


public class YunbiTask extends TradeTask {

    private Double amount;
    private Double price;
    private Long orderId;
    private SymbolPair symbolPair;

    YunbiTask(Operation operation) {
        super(operation);
    }

    @Override
    protected Object doTask(Operation operation) {
        AbstractMarketApi market = MarketApiFactory.getInstance().getMarket(Market.PeatioCNY);
        AppAccount appAccount = YunbiConfig.getAppAccount();

        switch (operation){

            case BUY:
                return market.buy(appAccount,amount,price,symbolPair);
            case ORDERS:
                return market.getRunningOrders(appAccount);
            case SELL:
                return market.sell(appAccount,amount,price,symbolPair);
            case CANCEL:
                 market.cancel(appAccount,orderId,symbolPair);
        }

        return null;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setSymbolPair(SymbolPair symbolPair) {
        this.symbolPair = symbolPair;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
