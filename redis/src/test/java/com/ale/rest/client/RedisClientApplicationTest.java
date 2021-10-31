package com.ale.rest.client;

import com.ale.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.LocalDateTime;

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

    @Test
    void testRedissonBucket() {
        RBucket<User> string = redissonClient.getBucket("string");
        User user = new User();
        user.setId(1);
        user.setName("jack");
        user.setBirthday(LocalDateTime.now());
        string.set(user);
        log.info("{}", string);
    }

    @Test
    void testRedissonGetBucket() {
        RBucket<User> string = redissonClient.getBucket("string");
        User user = string.get();
        log.info("{}", user);
    }

    @Test
    void testRedissonTryBucket() {
        RBucket<User> string = redissonClient.getBucket("string");
        User user = new User();
        user.setId(1);
        user.setName("jack");
        user.setBirthday(LocalDateTime.now());
        boolean b = string.trySet(user);
        log.info("{}", b);
    }

    @Test
    void testBloomFilter(){
        RBloomFilter<String> bloomFilter = redissonClient.getBloomFilter("phoneList");
        bloomFilter.tryInit(10000000L, 0.03);
        bloomFilter.add("10010");
        Assertions.assertFalse(bloomFilter.contains("12306"));
        Assertions.assertTrue(bloomFilter.contains("10010"));
    }
}