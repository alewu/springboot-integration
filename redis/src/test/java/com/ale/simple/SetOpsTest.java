package com.ale.simple;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;

/**
 * @author alewu
 * @date 2020/6/8
 */
@DataRedisTest
class SetOpsTest {
    @Autowired
    private RedisTemplate<String, String> strRedisTemplate;


    @Test
    void testSet() {
        String key = "link_relation_id:1:user_switch_list";
        System.out.println(strRedisTemplate.opsForSet().members(key));
        System.out.println(strRedisTemplate.getExpire(key));
        //        System.out.println(strRedisTemplate.expire(key, 1, TimeUnit.MILLISECONDS));
    }

    @Test
    void testSetOperate() {
        String keyView = "keyView";
        String keyPass = "keyPass";
        strRedisTemplate.opsForSet().add(keyView, "1", "2", "3");
        strRedisTemplate.opsForSet().add(keyPass, "1");
        Set<String> difference = strRedisTemplate.opsForSet().difference(keyView, keyPass);
        System.out.println(difference);
    }


}
