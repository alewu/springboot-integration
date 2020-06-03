package com.ale;

import com.ale.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class MySpringBootRedisApplicationTest {
    @Autowired
    private RedisTemplate<String, String> strRedisTemplate;
    @Autowired
    private RedisTemplate<String, Object> serializableRedisTemplate;

    @Test
    public void testString() {
        strRedisTemplate.opsForValue().set("strKey", "zwqh");
        System.out.println(strRedisTemplate.opsForValue().get("strKey"));
    }

    @Test
    public void testSerializable() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setUserName("jack");
        user.setUserSex("man");
        serializableRedisTemplate.opsForValue().set("user", user);
        UserEntity user2 = (UserEntity) serializableRedisTemplate.opsForValue().get("user");
        System.out.println("user:" + user2.getId() + "," + user2.getUserName() + "," + user2.getUserSex());
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
    public void testDel() {
        String key = "qr_code*";
        Set<String> keys = strRedisTemplate.keys(key);
        if (keys != null) {
            strRedisTemplate.delete(keys);
        }
    }


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
    public void testHash() {
        String key = "anonymous_user_id:a48b313f8f5911eaa342-525400236ced:qr_code_switch_count";
        strRedisTemplate.opsForHash().put("hidden_qr_code", "a48b313f8f5911eaa342-525400236ced", "1111111111111111");
        System.out.println(strRedisTemplate.opsForHash().get("hidden_qr_code", "a48b313f8f5911eaa342-525400236ced"));
        System.out.println(strRedisTemplate.getExpire(key));
        System.out.println(strRedisTemplate.expire(key, 10, TimeUnit.MILLISECONDS));
    }

    @Test
    public void testSetW() {
        String key = "test";
        //        add(key);
        Set<ZSetOperations.TypedTuple<String>> test = strRedisTemplate.opsForZSet().rangeByScoreWithScores(key,
                                                                                                           0.0, 2.0,
                                                                                                           0, 1);
        for (ZSetOperations.TypedTuple<String> stringTypedTuple : test) {
            System.out.println(stringTypedTuple.getValue());
            System.out.println(stringTypedTuple.getScore());
        }

    }

    @Test
    public void testGetFirstElement() {
        BoundZSetOperations<String, String> zSetOperations = strRedisTemplate.boundZSetOps("test1");
        Set<ZSetOperations.TypedTuple<String>> typedTuples = Optional.ofNullable(zSetOperations.rangeWithScores(0, 0)).orElse(Collections.emptySet());
        String value = typedTuples.stream().findFirst().map(ZSetOperations.TypedTuple::getValue).orElse("");
        Double score = typedTuples.stream().findFirst().map(ZSetOperations.TypedTuple::getScore).orElse(0.0);
        System.out.println(value);
        zSetOperations.incrementScore(value, 1.0);

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