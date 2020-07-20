package com.ale.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * The type Async task service.
 *
 * @author alewu
 * @date 2020 /6/21
 */
@Service
@Slf4j
public class AsyncSmallTaskService {

    /**
     * Small task completable future.
     *
     * @param i the
     * @return the completable future
     * @throws InterruptedException the interrupted exception
     */
    @Async("smallTaskExecutor")
    public CompletableFuture<String> smallTask(int i) throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(1000);
        long currentTimeMillis1 = System.currentTimeMillis();
        log.info("task {} with completable future 任务耗时: {} ms", i, (currentTimeMillis1 - currentTimeMillis));
        return CompletableFuture.completedFuture("success");
    }
}
