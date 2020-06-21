package com.ale.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
  *
  * @author alewu
  * @date 2020/6/21
  */
@Service
@Slf4j
public class AsyncTaskService {
    @Async
    public void task1() throws InterruptedException{
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(1000);
        long currentTimeMillis1 = System.currentTimeMillis();
        log.info("task1任务耗时: {} ms", (currentTimeMillis1-currentTimeMillis));
    }

    @Async
    public void task2() throws InterruptedException{
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(2000);
        long currentTimeMillis1 = System.currentTimeMillis();
        log.info("task2任务耗时: {} ms", (currentTimeMillis1-currentTimeMillis));
    }
    @Async
    public void task3() throws InterruptedException{
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(3000);
        long currentTimeMillis1 = System.currentTimeMillis();
        log.info("task3任务耗时: {} ms", (currentTimeMillis1-currentTimeMillis));
    }
}
