package com.ale.rabbitmq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

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
        int corePoolSize = Runtime.getRuntime().availableProcessors() * 200;
        executor.setCorePoolSize(corePoolSize);
        // 设置最大线程数
        executor.setMaxPoolSize(corePoolSize + 10);

        executor.setQueueCapacity(10000);

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

}
