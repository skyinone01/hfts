package com.ug369.backend.service.component.Task;

import com.ug369.backend.service.component.Bean.TradePolicy;

import java.util.concurrent.Callable;

;

/**
 * Created by Roy on 2017/8/3.
 */
public abstract class TradeTask implements Callable{

    private Operation operation;

    private TradePolicy tradePolicy;

    TradeTask(Operation operation,TradePolicy tradePolicy){
        this.operation = operation;
        this.tradePolicy = tradePolicy;
    }

    @Override
    public Object call() throws Exception {
        return doTask(operation);
    }

    abstract protected Object doTask(Operation operation) throws Exception;

    public Operation getOperation(){
        return operation;
    }

    public TradePolicy getTradePolicy() {
        return tradePolicy;
    }
}
