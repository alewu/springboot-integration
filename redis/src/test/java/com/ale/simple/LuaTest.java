package com.ale.simple;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class LuaTest {
    @Resource
    private DefaultRedisScript<Boolean> redisScript;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test() {
        List<String> list = Arrays.asList("testLua", "hello lua", "100");
        Boolean execute = stringRedisTemplate.execute(redisScript, list, "1");
        Assertions.assertEquals(true, execute);
    }
}
