package com.ale.redis.client.redisson;

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

    @Bean("msgExecutorPool")
    public ThreadPoolExecutor customExecutorPool() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("msg-recv-task" + "-%d")
                .setDaemon(true).build();

        int corePoolSize = 2 * Runtime.getRuntime().availableProcessors();
        return new ThreadPoolExecutor(corePoolSize, 200,
                                      1000L, TimeUnit.MILLISECONDS,
                                      new ArrayBlockingQueue<>(10000), threadFactory,
                                      new ThreadPoolExecutor.CallerRunsPolicy());
    }

    @Bean("msgSendExecutorPool")
    public ThreadPoolExecutor msgSendExecutorPool() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("msg-send-task" + "-%d")
                .setDaemon(true).build();

        int corePoolSize = 2 * Runtime.getRuntime().availableProcessors();
        return new ThreadPoolExecutor(corePoolSize, 200,
                                      1000L, TimeUnit.MILLISECONDS,
                                      new ArrayBlockingQueue<>(10000), threadFactory,
                                      new ThreadPoolExecutor.CallerRunsPolicy());
    }

    @Bean("takeExecutorPool")
    public ThreadPoolExecutor takeExecutorPool() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("take-task" + "-%d")
                .setDaemon(true).build();

        return new ThreadPoolExecutor(2, 2,
                                      1000L, TimeUnit.MILLISECONDS,
                                      new ArrayBlockingQueue<>(10000), threadFactory,
                                      new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
