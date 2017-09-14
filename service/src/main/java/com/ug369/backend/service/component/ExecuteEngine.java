package com.ug369.backend.service.component;

import com.ug369.backend.service.component.Bean.TradePolicy;
import com.ug369.backend.service.component.Task.TradeTask;
import com.ug369.backend.service.component.Thread.AbstractRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Roy on 2017/8/3.
 */
public class ExecuteEngine extends AbstractRunner {

    @Autowired
    private PipeLine pipeLine;

    private LinkedBlockingQueue<TradePolicy> policies = new LinkedBlockingQueue<>();

    public void addPolicy(TradePolicy policy){
        policies.add(policy);
    }


    @Override
    protected void doBusiness() throws InterruptedException, ExecutionException {

        TradePolicy policy = policies.take();
        pipeLine.addTradeTask();

    }

    public List<TradeTask> generateTask(TradePolicy policy){
        switch (policy.getMarket()){

        }
    }
}
