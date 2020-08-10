package com.ale.client;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Slf4j
class RedissonClientApplicationTest {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedissonClient redissonClient;

    @Test
    void test() {
        RedisConnectionFactory connectionFactory = stringRedisTemplate.getConnectionFactory();
        log.info("connectionFactory：{}", connectionFactory.getConnection().getClientName());
    }

    @Test
    void testRedisson() {
        String id = redissonClient.getId();
        log.info("{}", id);
        Assertions.assertNotNull(id);
    }

    @Test
    void testDelay() {
        RBlockingQueue<String> blockingQueue = redissonClient.getBlockingQueue("RDelayedQueue");
        RDelayedQueue<String> delayedQueue = redissonClient.getDelayedQueue(blockingQueue);
        delayedQueue.offer("11", 10, TimeUnit.SECONDS);
        delayedQueue.offer("22", 20, TimeUnit.SECONDS);
        delayedQueue.offer("33", 30, TimeUnit.SECONDS);
        delayedQueue.offer("100", 300, TimeUnit.SECONDS);
    }
}