package com.ug369.backend.service.component.Thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;

/**
 * Created by Roy on 2017/9/6.
 */
public abstract class AbstractRunner implements Runnable{

    private static Logger logger = LoggerFactory.getLogger(AbstractRunner.class);

    @Override
    public void run() {
        try {
            while (true){
                try {
                    doBusiness();
                }catch (Exception e){
                    logger.error("error occur when exe task ",e);
                    Thread.sleep(100);
                }
            }
        }catch (Exception e){
            logger.error("error occur when sleep ",e);
        }
    }

    protected abstract void doBusiness() throws InterruptedException, ExecutionException;
}
