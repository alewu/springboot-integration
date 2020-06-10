package com.ale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;

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

    @Test
    public void testSetObject() {
        //        listOperations.rightPush();

    }

}
