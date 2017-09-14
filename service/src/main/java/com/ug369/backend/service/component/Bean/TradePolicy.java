package com.ug369.backend.service.component.Bean;

import java.util.Date;

/**
 * Created by Roy on 2017/9/7.
 */
public class TradePolicy {

    private long userId;//用户
    private long platform;//市场
    private Date startTime;
    private Date endTime;
    private Double maxPrice;//最高价格阈值
    private Double minPrice;//最低价格阈值
    private Double amount;//每次交易量
    private Double initPrice;//初始价格
    private Double buyPrice;
    private Double sellPrice;
    private Double rate;
    private Double balance;
    private Symbol symbol;
    private boolean rateOrBalance;//0 by rate, 1 by balance
    private boolean status;//0  stop, 1 start
    private String market;

    TradePolicy(long userId,long platform,Date startTime,Date endTime,Double maxPrice,Double minPrice,Double amount,
                Double initPrice,Double balance,Double rate,boolean rateOrBalance,boolean status,Symbol symbol){
        this.userId =userId;
        this.platform =platform;
        this.amount =amount;
        this.startTime =startTime;
        this.endTime =endTime;
        this.maxPrice =maxPrice;
        this.minPrice =minPrice;
        this.initPrice =initPrice;
        this.balance =balance;
        this.rate =rate;
        this.rateOrBalance =rateOrBalance;
        this.status =status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public synchronized Double getNextBuyPrice(){

        return buyPrice;
    }

    public synchronized Double getNextSellPrice(){

        return buyPrice;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getPlatform() {
        return platform;
    }

    public void setPlatform(long platform) {
        this.platform = platform;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public boolean isRateOrBalance() {
        return rateOrBalance;
    }

    public void setRateOrBalance(boolean rateOrBalance) {
        this.rateOrBalance = rateOrBalance;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getInitPrice() {
        return initPrice;
    }

    public void setInitPrice(Double initPrice) {
        this.initPrice = initPrice;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof TradePolicy){
            TradePolicy temp = (TradePolicy) obj;
            if (temp.getUserId() == this.userId && temp.getPlatform() == this.platform){
                return true;
            }
        }
        return false;
    }
}
