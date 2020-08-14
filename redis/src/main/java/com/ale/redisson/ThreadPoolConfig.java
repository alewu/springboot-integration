package com.ale.redisson;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author alewu
 * @date 2020/6/16
 */
@Configuration
public class ThreadPoolConfig {

    @Bean("customExecutorPool")
    public ThreadPoolExecutor customExecutorPool() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("custom-task" + "-%d")
                .setDaemon(true).build();

        int corePoolSize = 2 * Runtime.getRuntime().availableProcessors();
        return new ThreadPoolExecutor(corePoolSize, 200,
                                      1000L, TimeUnit.MILLISECONDS,
                                      new ArrayBlockingQueue<>(10000), threadFactory,
                                      new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
