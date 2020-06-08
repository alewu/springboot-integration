package com.ale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

/**
 * @author alewu
 * @date 2020/6/8
 */
@SpringBootTest
public class ListOpsTest {
    @Autowired
    private RedisTemplate<String, String> strRedisTemplate;
    public static BoundListOperations<String, String> listOperations = null;
    public static String key = "";

    @BeforeEach
    public void init() {
        key = "test_list";
        listOperations = strRedisTemplate.boundListOps(key);
    }

    @Test
    public void testListRightPush() {
        listOperations.rightPush("a");
        listOperations.rightPush("b");
        listOperations.rightPush("c");
        listOperations.rightPush("d");
        String index = listOperations.index(1);
        System.out.println("第一个：" + index);
    }

    private void add(String key) {
        ZSetOperations<String, String> zSetOperations = strRedisTemplate.opsForZSet();
        zSetOperations.add(key, "a", 4);
        zSetOperations.add(key, "b", 3);
        zSetOperations.add(key, "c", 2);
        zSetOperations.add(key, "d", 3);
        zSetOperations.add(key, "e", 3);
        zSetOperations.add(key, "f", 3);
        zSetOperations.add(key, "g", 3);
    }

}
