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
        RBlockingDeque<String> blockingDeque = redissonClient.getBlockingDeque("ready-queue-");
        RDelayedQueue<String> delayedQueue = redissonClient.getDelayedQueue(blockingDeque);
        int delay = 10;
        delayedJob.setDelayTime(ThreadLocalRandom.current().nextInt(10, 20));
        delayedQueue.offer(delayedJob.toString(), delay, TimeUnit.SECONDS);
    }

    @Async("msgSendExecutorPool")
    public void sendAsync(DelayedJob delayedJob) {
        log.info("send msg to delay queue, {}", delayedJob);
        RBlockingDeque<String> blockingDeque = redissonClient.getBlockingDeque("ready-queue");
        RDelayedQueue<String> delayedQueue = redissonClient.getDelayedQueue(blockingDeque);
        int delay = ThreadLocalRandom.current().nextInt(100);
        delayedJob.setDelayTime(delay);
        delayedQueue.offer(delayedJob.toString(), delay, TimeUnit.SECONDS);
    }
}
