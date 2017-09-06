package com.ug369.backend.service.component.Thread;

import com.ug369.backend.service.component.Task.CheckerTask;
import com.ug369.backend.service.component.Task.TradeTask;
import com.ug369.backend.service.component.ThreadExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Roy on 2017/8/3.
 */
public class Trader extends AbstractRunner{

    private static Logger logger = LoggerFactory.getLogger(Trader.class);

    protected BlockingQueue<TradeTask> taskQueue = new LinkedBlockingDeque<>();

    @Override
    protected void doBusiness() throws InterruptedException {
        TradeTask task = taskQueue.take();
        Future<Object> result = ThreadExecutor.submit(task);
        CheckerTask orderCheckerTask = new CheckerTask(task.getOperation(),result);


    }
}
