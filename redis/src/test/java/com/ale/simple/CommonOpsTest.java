package com.ale.simple;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StopWatch;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CommonOpsTest {
    @Autowired
    private StringRedisTemplate strRedisTemplate;


    @Test
    void testDel() {
        String key = "z_set";
        strRedisTemplate.delete(key);
    }

    @Test
    void testBatchDel() {
        Collection<String> keys = Lists.list("test_hash", "test_hash2");
        strRedisTemplate.delete(keys);
    }

    /**
     * key是否存在
     */
    @Test
    void testExists() {
        boolean isExists = Optional.ofNullable(strRedisTemplate.hasKey("test_hash")).orElse(false);
        Assertions.assertFalse(isExists);

    }

    @Test
    void testExpire() {
        strRedisTemplate.expire("", 1, TimeUnit.SECONDS);
    }

    /**
     * pattern是个类似正则表达式的查询规则 *匹配任意数量的符号，？匹配一个任意符号，[]匹配一个指定符号
     */
    @Test
    void testKeys() {
        // 查询所有
        Set<String> allKeys = strRedisTemplate.keys("*");
        System.out.println(allKeys);

        // 查询以a结尾
        Set<String> keysOfA = strRedisTemplate.keys("*a");
        System.out.println(keysOfA);

        // 查询以a结尾，长度为2
        Set<String> keys = strRedisTemplate.keys("?a");
        System.out.println(keys);

        // 查询以a结尾，长度为2
        Set<String> key = strRedisTemplate.keys("pig[a]");
        System.out.println(key);
    }

    @Test
    void testRename() {
        StopWatch sw = new StopWatch("CommonOpsTest.testRename");
        // 如果new key已经存在，则直接覆盖已存在的key
        sw.start("xx");
        strRedisTemplate.opsForValue().set("oldKey", "1");
        sw.stop();
        // new key不存在才能改名
        strRedisTemplate.renameIfAbsent("oldKey", "newKey");
        System.out.println(sw.prettyPrint());
    }
}
