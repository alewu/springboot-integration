package com.ale.redisson;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class MsgProducer {
    @Autowired
    private RedissonClient redissonClient;


    public void send(DelayedJob delayedJob) {
        log.info("send msg to delay queue, {}", delayedJob);
        RBlockingDeque<DelayedJob> blockingDeque = redissonClient.getBlockingDeque("delay-queue");
        RDelayedQueue<DelayedJob> delayedQueue = redissonClient.getDelayedQueue(blockingDeque);
        delayedQueue.offer(delayedJob, ThreadLocalRandom.current().nextInt(100), TimeUnit.SECONDS);
    }

    @Async("customExecutorPool")
    public void sendAsync(DelayedJob msg) {
        log.info("send msg to delay queue, {}", msg);
        RBlockingDeque<Object> blockingDeque = redissonClient.getBlockingDeque("delay-queue");
        RDelayedQueue<Object> delayedQueue = redissonClient.getDelayedQueue(blockingDeque);
        delayedQueue.offer(msg, ThreadLocalRandom.current().nextInt(100), TimeUnit.SECONDS);
    }
}
