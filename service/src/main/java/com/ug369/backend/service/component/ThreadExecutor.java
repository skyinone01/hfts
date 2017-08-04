package com.ug369.backend.service.component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Roy on 2017/8/3.
 */
public class ThreadExecutor {

    private static ExecutorService coreThreadPool = Executors.newCachedThreadPool();

    private static ExecutorService transientThreadPool = Executors.newWorkStealingPool();

    public static void executeCoreTask(Runnable immortalTask) {
        coreThreadPool.execute(immortalTask);
    }

    public static void executeTransientTask(Runnable immortalTask) {
        transientThreadPool.execute(immortalTask);
    }

    public static void shutdown() {
        coreThreadPool.shutdown();
        transientThreadPool.shutdownNow();
    }

    public static boolean awaitTermination(long timeout, TimeUnit unit)
            throws InterruptedException {
        return coreThreadPool.awaitTermination(timeout, unit);
    }

    public static boolean isTerminated() {
        return coreThreadPool.isTerminated() && transientThreadPool.isTerminated();
    }
}
