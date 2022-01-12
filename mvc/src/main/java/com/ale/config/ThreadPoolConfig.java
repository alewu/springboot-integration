package com.ale.config;


import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/**
 * @author alewu
 * @date 2020/6/21
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig {

    @Bean
    public AsyncTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("Custom-task-");
        // default 1
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(10);

        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                // .....
            }
        });
        // 使用预定义的异常处理类
        // executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 链路追踪
        executor.setTaskDecorator(new MDCTaskDecorator());

        return executor;
    }

    @Bean("smallTaskExecutor")
    public AsyncTaskExecutor smallTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("small-task-");
        // default 1
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(10);

        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
                // .....
            }
        });

        return executor;
    }


    @Bean("delayedMsgExecutorPool")
    public ThreadPoolExecutor delayedMsgExecutorPool() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("delayed-msg-thread" + "-%d")
                .setDaemon(true).build();
        int i = Runtime.getRuntime().availableProcessors();

        return new ThreadPoolExecutor(i, i + 10,
                                      0L, TimeUnit.MILLISECONDS,
                                      new ArrayBlockingQueue<>(10000), threadFactory,
                                      new ThreadPoolExecutor.CallerRunsPolicy());
    }


}
