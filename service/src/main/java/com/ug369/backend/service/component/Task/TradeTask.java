package com.ug369.backend.service.component.Task;

;

import java.util.concurrent.Callable;

/**
 * Created by Roy on 2017/8/3.
 */
public abstract class TradeTask implements Callable{

    private Operation operation;

    TradeTask(Operation operation){
        this.operation = operation;
    }

    @Override
    public Object call() throws Exception {
        return doTask(operation);
    }

    abstract protected Object doTask(Operation operation);

    public Operation getOperation(){
        return operation;
    }
}
