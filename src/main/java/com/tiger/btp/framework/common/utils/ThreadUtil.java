package com.tiger.btp.framework.common.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.*;

/**
 * 线程辅助类
 *
 * @author ShenHuaJie
 * @since 2018年7月27日 下午7:00:11
 */
public final class ThreadUtil {
    static Logger logger = LogManager.getLogger();

    public static void sleep(int start, int end) {
        try {
            Thread.sleep(MathUtil.getRandom(start, end).longValue());
        } catch (InterruptedException e) {
            logger.error(e.getStackTrace().toString());
        }
    }

    public static void sleep(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            if (logger.isDebugEnabled()) {
                logger.debug(e.getMessage());
            }
        }
    }

    public static ExecutorService threadPool(int core, int max, int seconds) {
        return new ThreadPoolExecutor(core, max, seconds, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable runnable) {
                        Thread thread = new Thread(runnable, "System Clock");
                        return thread;
                    }
                });
    }
}
