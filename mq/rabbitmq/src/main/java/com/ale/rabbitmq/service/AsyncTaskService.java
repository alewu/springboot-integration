package com.ale.rabbitmq.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author alewu
 * @date 2020/6/21
 */
@Service
@Slf4j
public class AsyncTaskService {

    @Async(value = "msgTaskExecutor")
    public void sendMsgAsync(String msg) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("send message: {} ", msg);
    }

    public void sendMsg(String msg) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("send message: {} ", msg);
    }
}
