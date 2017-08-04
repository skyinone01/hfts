package com.ug369.backend.service.component.Thread;

import com.ug369.backend.service.component.Task.TradeTask;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Roy on 2017/8/3.
 */
public class Trader implements Runnable{

    private BlockingQueue<TradeTask> taskQueue = new LinkedBlockingDeque<TradeTask>();

    @Override
    public void run() {

        while (true){

            TradeTask task = taskQueue.poll();
        }
    }
}
