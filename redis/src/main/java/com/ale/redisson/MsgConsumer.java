package com.ale.redisson;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ThreadPoolExecutor;

@Component
@Slf4j
public class MsgConsumer {
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    @Qualifier("takeExecutorPool")
    private ThreadPoolExecutor poolExecutor;
    @Autowired
    @Qualifier("msgExecutorPool")
    private ThreadPoolExecutor msgPoolExecutor;
    @Autowired
    private MsgHandler msgHandler;

    @PostConstruct
    public void recv() {
        RBlockingDeque<DelayedJob> blockingDeque = redissonClient.getBlockingDeque("ready-queue");
        for (int i = 0; i < 2; i++) {
            poolExecutor.execute(() -> {
                while (!Thread.interrupted()) {
                    try {
                        Object take = blockingDeque.take();
                        log.info("take from queue {}", take);
                        msgPoolExecutor.execute(() -> msgHandler.handle(take.toString()));
                    } catch (InterruptedException e) {
                        log.error("take failed", e);
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }

    }


}
