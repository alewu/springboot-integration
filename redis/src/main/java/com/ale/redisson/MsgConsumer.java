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
    @Qualifier("customExecutorPool")
    private ThreadPoolExecutor poolExecutor;

    @PostConstruct
    public void recv() {
        RBlockingDeque<Object> test = redissonClient.getBlockingDeque("test");
        poolExecutor.execute(() -> {
            while (true) {
                try {
                    Object take = test.take();
                    log.info("get from delay queue, {}", take);
                } catch (InterruptedException e) {
                    log.error("aaaa", e);
                }
            }
        });
    }
}
