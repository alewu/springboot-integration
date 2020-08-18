package com.ale.redisson;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class MsgConsumer {
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    @Qualifier("customExecutorPool")
    private ThreadPoolExecutor poolExecutor;

    @PostConstruct
    public void recv() {
        RBlockingDeque<DelayedJob> blockingDeque = redissonClient.getBlockingDeque("delay-queue");

        blockingDeque.add(new DelayedJob());
        poolExecutor.execute(() -> {
            while (true) {
                try {
                    Object take = blockingDeque.take();
                    TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
                    log.info("get from delay queue, {}", take);
                } catch (InterruptedException e) {
                    log.error("aaaa", e);
                }
            }
        });
    }


}
