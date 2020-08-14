package com.ale.client;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Slf4j
class RedissonClientApplicationTest {

    @Autowired
    RedissonClient redissonClient;
    //
    //    @Test
    //    void test() {
    //        RedisConnectionFactory connectionFactory = stringRedisTemplate.getConnectionFactory();
    //        log.info("connectionFactory：{}", connectionFactory.getConnection().getClientName());
    //    }

    @Test
    void testRedisson() {
        String id = redissonClient.getId();
        log.info("{}", id);
        Assertions.assertNotNull(id);
    }

    @Test
    void testDelay() throws InterruptedException, ExecutionException {
        RBlockingQueue<String> blockingQueue = redissonClient.getBlockingQueue("RDelayedQueue");
        RDelayedQueue<String> delayedQueue = redissonClient.getDelayedQueue(blockingQueue);
        //        delayedQueue.offer("11", 10, TimeUnit.SECONDS);
        //        delayedQueue.offer("22", 20, TimeUnit.SECONDS);
        //        delayedQueue.offer("33", 30, TimeUnit.SECONDS);
        //        delayedQueue.offer("100", 300, TimeUnit.SECONDS);
        //        TimeUnit.SECONDS.sleep(30);
        RBlockingDeque<Object> rDelayedQueue = redissonClient.getBlockingDeque("RDelayedQueue");
        List<RFuture<Boolean>> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            RFuture<Boolean> booleanRFuture = rDelayedQueue.removeAsync("100");
            list.add(booleanRFuture);
        }
        List<Boolean> collect = list.stream().map(booleanRFuture -> {
            try {
                return booleanRFuture.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return false;
        }).collect(Collectors.toList());
        for (Boolean aBoolean : collect) {
            log.info("remove result:{}", aBoolean);
        }

    }
}