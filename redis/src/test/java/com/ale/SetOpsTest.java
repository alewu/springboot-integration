package com.ale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

/**
 * @author alewu
 * @date 2020/6/8
 */
@SpringBootTest
public class SetOpsTest {
    @Autowired
    private RedisTemplate<String, String> strRedisTemplate;


    @Test
    public void testSet() {
        String key = "link_relation_id:1:user_switch_list";
        System.out.println(strRedisTemplate.opsForSet().members(key));
        System.out.println(strRedisTemplate.getExpire(key));
        //        System.out.println(strRedisTemplate.expire(key, 1, TimeUnit.MILLISECONDS));
    }

    @Test
    public void testSetOperate() {
        String keyView = "keyView";
        String keyPass = "keyPass";
        strRedisTemplate.opsForSet().add(keyView, "1", "2", "3");
        strRedisTemplate.opsForSet().add(keyPass, "1");
        Set<String> difference = strRedisTemplate.opsForSet().difference(keyView, keyPass);
        System.out.println(difference);
    }

    @Test
    public void testSetWithScore() {
        String key = "test";
        //        add(key);
        Set<ZSetOperations.TypedTuple<String>> typedTuples = strRedisTemplate.opsForZSet().rangeByScoreWithScores(key,
                                                                                                                  0.0
                , 2.0,
                                                                                                                  0, 1);
        for (ZSetOperations.TypedTuple<String> stringTypedTuple : typedTuples) {
            System.out.println(stringTypedTuple.getValue());
            System.out.println(stringTypedTuple.getScore());
        }

    }

    @Test
    public void testGetFirstElement() {
        BoundZSetOperations<String, String> zSetOperations = strRedisTemplate.boundZSetOps("z_set");
        Set<ZSetOperations.TypedTuple<String>> typedTuples =
                Optional.ofNullable(zSetOperations.rangeWithScores(0, 0)).orElse(Collections.emptySet());
        String value = typedTuples.stream().findFirst().map(ZSetOperations.TypedTuple::getValue).orElse("");
        Double score = typedTuples.stream().findFirst().map(ZSetOperations.TypedTuple::getScore).orElse(0.0);
        System.out.println(value);
        zSetOperations.incrementScore(value, 1.0);
        zSetOperations.incrementScore(value, 1.0);
    }

}
