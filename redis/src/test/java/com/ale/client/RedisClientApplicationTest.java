package com.ale.client;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
@Slf4j
class RedisClientApplicationTest {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    void test() {
        RedisConnectionFactory connectionFactory = stringRedisTemplate.getConnectionFactory();
        log.info("connectionFactory：{}", connectionFactory.getConnection().getClientName());
    }
}