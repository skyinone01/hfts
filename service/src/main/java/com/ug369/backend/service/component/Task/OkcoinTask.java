package com.ug369.backend.service.component.Task;

import com.okcoin.rest.stock.IStockRestApi;
import com.okcoin.rest.stock.impl.StockRestApi;
import com.ug369.backend.service.component.Bean.Symbol;
import com.ug369.backend.service.component.Bean.TradePolicy;


public class OkcoinTask extends TradeTask {

    private Double amount;
    private Double price;
    private Long orderId;
    private Symbol symbol;
    private String api_key = "162fb13d-ad86-429b-8ec1-f10f214af35e";  //TODO OKCoin申请的apiKey
    private String secret_key = "5398E4646EF1080A78EFEA6B65BE9426";  //TODO OKCoin申请的secretKey
    private static  final  String URL_PREX = "https://www.okcoin.cn";  //注意：请求URL 国际站https://www.okcoin.com ; 国内站https://www.okcoin.cn

    public OkcoinTask(Operation operation,Symbol symbol, TradePolicy tradePolicy,String api_key,String secret_key) {
        super(operation,tradePolicy);
        this.api_key = api_key;
        this.secret_key =secret_key;
        this.symbol = symbol;
    }

    @Override
    protected Object doTask(Operation operation) throws Exception{

        IStockRestApi stockGet;
        IStockRestApi stockPost;
        switch (operation){
            case BUY:
                stockPost = new StockRestApi(URL_PREX, api_key, secret_key);
                return stockPost.trade(symbol.toString(),"buy",String.valueOf(price),String.valueOf(amount));
            case ORDERS:
                stockGet = new StockRestApi(URL_PREX);
                return stockGet.order_info("btc_usd",String.valueOf(orderId));
            case SELL:
                stockPost = new StockRestApi(URL_PREX, api_key, secret_key);
                return stockPost.trade(symbol.toString(),"sell",String.valueOf(price),String.valueOf(amount));
            case CANCEL:
                stockPost = new StockRestApi(URL_PREX, api_key, secret_key);
                stockPost.cancel_order(symbol.toString(),String.valueOf(orderId));
        }
        return null;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
