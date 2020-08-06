package com.ale.client;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
@Slf4j
class RedisClientApplicationTest {
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
}