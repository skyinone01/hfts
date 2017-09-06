package com.ug369.backend.service.component.Task;

import java.util.concurrent.Future;

/**
 * Created by Roy on 2017/9/6.
 */
public class CheckerTask {

    private Operation operation;
    private Future future;

    public CheckerTask(Operation operation, Future future){
        this.operation = operation;
        this.future = future;
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
}
