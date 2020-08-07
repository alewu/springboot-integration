package com.ale.simple;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author alewu
 * @date 2020/6/8
 */
@DataRedisTest
class ListOpsTest {
    @Autowired
    private RedisTemplate<String, String> strRedisTemplate;
    static BoundListOperations<String, String> listOperations = null;
    static String key = "";

    @BeforeEach
    void init() {
        key = "test_list";
        listOperations = strRedisTemplate.boundListOps(key);
    }

    @Test
    void testListRightPush() {
        listOperations.rightPush("a");
        listOperations.rightPush("b");
        listOperations.rightPush("c");
        listOperations.rightPush("d");
        String index = listOperations.index(1);
        System.out.println("第一个：" + index);
    }

    @Test
    void testSetObject() {
        //        listOperations.rightPush();

    }

    @Test
    void testbLPop() {
        while (true) {
            Long size = Optional.ofNullable(listOperations.size()).orElse(0L);
            if (size > 0) {
                String s = listOperations.leftPop(0, TimeUnit.SECONDS);
                System.out.println(s);
            }
        }

    }

}
