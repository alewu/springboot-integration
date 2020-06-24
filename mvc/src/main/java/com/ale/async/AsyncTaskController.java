package com.ale.async;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public String doTask() throws InterruptedException{
        long currentTimeMillis = System.currentTimeMillis();
        asyncTaskService.task1();
        asyncTaskService.task2();
        asyncTaskService.task3();
        long currentTimeMillis1 = System.currentTimeMillis();
        return "task任务总耗时:"+(currentTimeMillis1-currentTimeMillis)+"ms";
    }



}
