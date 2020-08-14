package com.ale.rabbitmq.exchangetype.service;

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

    @Async(value = "msgTaskExecutor1")
    public void sendMsgAsync1(String msg) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("send message: {} ", msg);
    }

    @Async(value = "msgTaskExecutor2")
    public void sendMsgAsync2(String msg) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("send message: {} ", msg);
    }

    @Async(value = "msgTaskExecutor3")
    public void sendMsgAsync3(String msg) {
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
