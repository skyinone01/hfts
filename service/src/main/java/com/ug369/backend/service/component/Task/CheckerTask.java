package com.ug369.backend.service.component.Task;

import com.ug369.backend.service.component.Bean.TradePolicy;

import java.util.concurrent.Future;

/**
 * Created by Roy on 2017/9/6.
 */
public class CheckerTask {

    private Operation operation;
    private Future future;
    private TradePolicy tradePolicy;


    public CheckerTask(Operation operation, Future future,TradePolicy tradePolicy){
        this.operation = operation;
        this.future = future;
        this.tradePolicy = tradePolicy;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Future getFuture() {
        return future;
    }

    public void setFuture(Future future) {
        this.future = future;
    }

    public TradePolicy getTradePolicy() {
        return tradePolicy;
    }

    public void setTradePolicy(TradePolicy tradePolicy) {
        this.tradePolicy = tradePolicy;
    }
}
