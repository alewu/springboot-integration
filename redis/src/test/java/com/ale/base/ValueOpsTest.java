package com.ale.base;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author alewu
 * @date 2020/6/8
 */
@SpringBootTest
public class ValueOpsTest {
    @Autowired
    private RedisTemplate<String, String> strRedisTemplate;

    @Test
    public void testString() {
        strRedisTemplate.opsForValue().set("strKey", "zwqh");
        System.out.println(strRedisTemplate.opsForValue().get("strKey"));
    }

    @Test
    public void testIncr() {
        String key = "anonymous_user_id:a48b313f8f5911eaa342-525400236ced:qr_code_switch_count";
        System.out.println(strRedisTemplate.opsForValue().increment(key));
        System.out.println(strRedisTemplate.opsForValue().get(key));
        System.out.println(strRedisTemplate.getExpire(key));
        System.out.println(strRedisTemplate.expire(key, 10, TimeUnit.MILLISECONDS));
    }

    @Test
    public void testMget() {
        // 获取所有(一个或多个)给定 key 的值

        List<String> keys = new ArrayList<>();
        List<String> strings = strRedisTemplate.opsForValue().multiGet(keys);


    }
}
