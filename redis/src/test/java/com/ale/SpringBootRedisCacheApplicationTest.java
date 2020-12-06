package com.ale;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
@SpringBootTest
public class SpringBootRedisCacheApplicationTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testString() {
        stringRedisTemplate.opsForValue().set("strKey", "zwqh");
        System.out.println(stringRedisTemplate.opsForValue().get("strKey"));
    }
}