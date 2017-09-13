package com.ug369.backend.service.component;

import com.ug369.backend.service.component.Bean.TradePolicy;
import com.ug369.backend.service.component.Task.CheckerTask;
import com.ug369.backend.service.component.Task.TradeTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Roy on 2017/9/13.
 */
@Component
public class PipeLine {
    private static Logger logger = LoggerFactory.getLogger(PipeLine.class);
    private  BlockingQueue<CheckerTask> checkerQueue = new LinkedBlockingDeque<>();
    private  BlockingQueue<TradeTask>  tradeTaskQueue= new LinkedBlockingDeque<>();

    public TradeTask takeTradeTask(){
        try {
            return tradeTaskQueue.take();
        } catch (Exception e) {
            logger.error("error occur when poll tradeTask ", e);
        }
        return null;
    }


    public void addTradeTask(TradeTask task){
        try {
            tradeTaskQueue.offer(task);
        } catch (Exception e) {
            logger.error("error occur when add tradeTask ", e);
        }
    }

    public void addCheckerTask(CheckerTask task){
        try {
            checkerQueue.offer(task);
        } catch (Exception e) {
            logger.error("error occur when add checkerTask ", e);
        }
    }

    public CheckerTask takeCheckerTask(){
        try {
            return checkerQueue.take();
        } catch (Exception e) {
            logger.error("error occur when take checkerTask ", e);
        }
        return null;
    }

    public synchronized void stopOrderChecker(TradePolicy policy){

        CheckerTask[] tasks = new CheckerTask[checkerQueue.size()];
        tasks = checkerQueue.toArray(tasks);
        if (tasks!=null && tasks.length>0){
            CheckerTask task;
            for (int i=0 ; i < tasks.length;i++){
                task = tasks[i];
                if (task.getTradePolicy().equals(policy)){
                    checkerQueue.remove(task);
                }
            }
        }
    }

    public int checkerSize(){
        return checkerQueue.size();
    }

    public int traderSzie(){
        return tradeTaskQueue.size();
    }



}
