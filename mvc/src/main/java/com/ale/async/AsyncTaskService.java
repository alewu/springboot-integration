package com.ale.async;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * The type Async task service.
 *
 * @author alewu
 * @date 2020 /6/21
 */
@Service
@Slf4j
@AllArgsConstructor
public class AsyncTaskService {
    private final AsyncSmallTaskService asyncSmallTaskService;


    /**
     * Task 1.没有返回值的异步调用
     *
     * @throws InterruptedException the interrupted exception
     */
    @Async
    public void taskWithVoid() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(1000);
        long currentTimeMillis1 = System.currentTimeMillis();
        log.info("task with void 任务耗时: {} ms", (currentTimeMillis1 - currentTimeMillis));
    }

    /**
     * Task 2.带有返回值的异步方法;回调函数
     *
     * @throws InterruptedException the interrupted exception
     */
    @Async
    public Future<String> taskWithResult() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(2000);
        Future<String> future = new AsyncResult<>("success");
        long currentTimeMillis1 = System.currentTimeMillis();
        log.info("task2 with future 任务耗时: {} ms", (currentTimeMillis1 - currentTimeMillis));
        return future;
    }

    /**
     * Task 3.
     *
     * @throws InterruptedException the interrupted exception
     */
    @Async
    public CompletableFuture<String> taskWithCompletableFuture() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(3000);
        long currentTimeMillis1 = System.currentTimeMillis();
        log.info("task with completable future 任务耗时: {} ms", (currentTimeMillis1 - currentTimeMillis));
        return CompletableFuture.completedFuture("success");
    }


    /**
     * Task 4.
     *
     * @throws InterruptedException the interrupted exception
     */
    @Async
    public CompletableFuture<String> taskEmbedSmallTask() throws InterruptedException {

        Thread.sleep(3000);

        long currentTimeMillis = System.currentTimeMillis();
        List<CompletableFuture<String>> futures = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            CompletableFuture<String> completableFuture = asyncSmallTaskService.smallTask(i);
            futures.add(completableFuture);
        }
        List<String> collect = futures.stream().map(CompletableFuture::join).collect(Collectors.toList());
        long currentTimeMillis1 = System.currentTimeMillis();


        log.info("task with completable future 任务耗时: {} ms, {}", (currentTimeMillis1 - currentTimeMillis), collect);
        return CompletableFuture.completedFuture("success");
    }
}
