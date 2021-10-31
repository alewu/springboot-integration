package com.ale.rest.client;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RFuture;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
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
    void testOffer() {
        RBlockingQueue<String> blockingQueue = redissonClient.getBlockingQueue("RDelayedQueue");
        RDelayedQueue<String> delayedQueue = redissonClient.getDelayedQueue(blockingQueue);
        delayedQueue.offer("11", 10, TimeUnit.SECONDS);
        delayedQueue.offer("22", 20, TimeUnit.SECONDS);
        delayedQueue.offer("33", 300, TimeUnit.SECONDS);
        delayedQueue.offer("100", 300, TimeUnit.SECONDS);
        delayedQueue.offer("101", 300, TimeUnit.SECONDS);
        delayedQueue.offer("102", 300, TimeUnit.SECONDS);
    }

    @Test
    void testDelay() throws InterruptedException, ExecutionException {
        RBlockingQueue<String> blockingQueue = redissonClient.getBlockingQueue("RDelayedQueue");
        RDelayedQueue<String> delayedQueue = redissonClient.getDelayedQueue(blockingQueue);
        TimeUnit.SECONDS.sleep(10);
        List<RFuture<Boolean>> list = new ArrayList<>();
        for (int i = 100; i < 103; i++) {
            RFuture<Boolean> booleanRFuture = delayedQueue.removeAsync("" + i);
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
        TimeUnit.SECONDS.sleep(300);
    }

    @Test
    void testRemove() throws InterruptedException, ExecutionException {
        RBlockingQueue<String> blockingQueue = redissonClient.getBlockingQueue("RDelayedQueue");
        RDelayedQueue<String> delayedQueue = redissonClient.getDelayedQueue(blockingQueue);
        List<String> list = Lists.newArrayList();
        for (int i = 100; i < 103; i++) {
            list.add("" + i);
        }
        RFuture<Boolean> booleanRFuture = delayedQueue.removeAllAsync(list);

        System.out.println(booleanRFuture.get());

        TimeUnit.SECONDS.sleep(50);
    }
}