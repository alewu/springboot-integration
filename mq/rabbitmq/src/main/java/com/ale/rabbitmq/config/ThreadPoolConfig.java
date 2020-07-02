package com.ale.rabbitmq.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
  *
  * @author alewu
  * @date 2020/6/21
  */
@Configuration
@EnableAsync
public class ThreadPoolConfig {

    @Bean("msgTaskExecutor")
    public AsyncTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置默认线程名称
        executor.setThreadNamePrefix("Msg-Executor-");
        // 设置核心线程数
        int corePoolSize = Runtime.getRuntime().availableProcessors() * 4;
        executor.setCorePoolSize(corePoolSize);
        // 设置最大线程数
        executor.setMaxPoolSize(corePoolSize + 10);

        executor.setQueueCapacity(1000);

         executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        return executor;
    }

    @Bean("msgTaskExecutor1")
    public AsyncTaskExecutor taskExecutor1() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置默认线程名称
        executor.setThreadNamePrefix("Middle-Executor-");
        // 设置核心线程数
        int corePoolSize = Runtime.getRuntime().availableProcessors() * 8;
        executor.setCorePoolSize(corePoolSize);
        // 设置最大线程数
        executor.setMaxPoolSize(corePoolSize + 10);

        executor.setQueueCapacity(1000);

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        return executor;
    }

    @Bean("msgTaskExecutor2")
    public AsyncTaskExecutor taskExecutor2() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置默认线程名称
        executor.setThreadNamePrefix("High-Executor-");
        // 设置核心线程数
        int corePoolSize = Runtime.getRuntime().availableProcessors() * 8;
        executor.setCorePoolSize(corePoolSize);
        // 设置最大线程数
        executor.setMaxPoolSize(corePoolSize + 10);

        executor.setQueueCapacity(1000);

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        return executor;
    }

    @Bean("msgTaskExecutor3")
    public AsyncTaskExecutor taskExecutor3() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置默认线程名称
        executor.setThreadNamePrefix("Slow-Executor-");
        // 设置核心线程数
        int corePoolSize = Runtime.getRuntime().availableProcessors() * 8;
        executor.setCorePoolSize(corePoolSize);
        // 设置最大线程数
        executor.setMaxPoolSize(corePoolSize + 10);

        executor.setQueueCapacity(1000);

        // 设置拒绝策略
        //        executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
        //            @Override
        //            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        //                // .....
        //            }
        //        });
        // 使用预定义的异常处理类
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        return executor;
    }

    @Bean("msgTaskExecutor2")
    public AsyncTaskExecutor taskExecutor7() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置默认线程名称
        executor.setThreadNamePrefix("Msg2-Executor-");
        // 设置核心线程数
        int corePoolSize = Runtime.getRuntime().availableProcessors() * 2;
        executor.setCorePoolSize(corePoolSize);
        // 设置最大线程数
        executor.setMaxPoolSize(corePoolSize + 10);

        executor.setQueueCapacity(1000);

        // 设置拒绝策略
        //        executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
        //            @Override
        //            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        //                // .....
        //            }
        //        });
        // 使用预定义的异常处理类
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        return executor;
    }

    @Bean("concurrentTaskExecutor")
    public ConcurrentTaskExecutor taskExecutor4() {
        ConcurrentTaskExecutor concurrentTaskExecutor = new ConcurrentTaskExecutor();
        // 设置核心线程数
        int corePoolSize = Runtime.getRuntime().availableProcessors() * 2;
        // 设置最大线程数
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("guava-%d").build();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, corePoolSize+2, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<>(1000), threadFactory);

        concurrentTaskExecutor.setConcurrentExecutor(executor);

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        return concurrentTaskExecutor;
    }

}
