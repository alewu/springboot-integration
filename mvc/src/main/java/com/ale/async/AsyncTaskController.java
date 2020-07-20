package com.ale.async;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

/**
 * @author alewu
 * @date 2020/6/24
 */
@RestController
@RequestMapping("")
@AllArgsConstructor
public class AsyncTaskController {
    private final AsyncTaskService asyncTaskService;

    @GetMapping("/async")
    public String doTask() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        asyncTaskService.taskWithVoid();
        asyncTaskService.taskWithResult();
        asyncTaskService.taskWithCompletableFuture();
        long currentTimeMillis1 = System.currentTimeMillis();
        return "task任务总耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms";
    }

    @GetMapping("/async/withResult")
    public String taskWithResult() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        asyncTaskService.taskWithVoid();
        asyncTaskService.taskWithResult();
        CompletableFuture<String> completableFuture = asyncTaskService.taskWithCompletableFuture();
        String join = completableFuture.join();
        long currentTimeMillis1 = System.currentTimeMillis();
        return "task任务总耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms " + "结果：" + join;
    }

    @GetMapping("/async/taskEmbedSmallTask")
    public String task() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        CompletableFuture<String> completableFuture = asyncTaskService.taskEmbedSmallTask();
        String join = completableFuture.join();
        long currentTimeMillis1 = System.currentTimeMillis();
        return "task任务总耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms " + "结果：" + join;
    }


}
