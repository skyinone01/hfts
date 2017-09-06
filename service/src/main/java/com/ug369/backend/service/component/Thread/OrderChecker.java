package com.ug369.backend.service.component.Thread;

import com.ug369.backend.service.component.Task.CheckerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Roy on 2017/8/3.
 */
public class OrderChecker extends AbstractRunner{

    private static Logger logger = LoggerFactory.getLogger(OrderChecker.class);

    protected BlockingQueue<CheckerTask> taskQueue = new LinkedBlockingDeque<>();

    @Override
    protected void doBusiness() throws InterruptedException, ExecutionException {
        CheckerTask task = taskQueue.take();
        if (task.getFuture().isDone()){
            switch (task.getOperation()){
                case BUY:
                    Long orderId = (Long) task.getFuture().get();
            }

        }else {
            taskQueue.put(task);
        }

    }
}
