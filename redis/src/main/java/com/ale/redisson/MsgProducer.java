package com.ale.redisson;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class MsgProducer {
    @Autowired
    private RedissonClient redissonClient;


    public void send(String msg) {
        log.info("send msg to delay queue, {}", msg);
        RBlockingDeque<Object> blockingDeque = redissonClient.getBlockingDeque("test");
        RDelayedQueue<Object> delayedQueue = redissonClient.getDelayedQueue(blockingDeque);
        delayedQueue.offer(msg, 100, TimeUnit.SECONDS);
    }

    @Async("customExecutorPool")
    public void sendAsync(String msg) {
        log.info("send msg to delay queue, {}", msg);
        RBlockingDeque<Object> blockingDeque = redissonClient.getBlockingDeque("test");
        RDelayedQueue<Object> delayedQueue = redissonClient.getDelayedQueue(blockingDeque);
        delayedQueue.offer(msg, 100, TimeUnit.SECONDS);
    }
}
