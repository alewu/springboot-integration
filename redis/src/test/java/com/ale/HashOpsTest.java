package com.ale;

import com.ale.entity.UserEntity;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author alewu
 * @date 2020/6/8
 */
@SpringBootTest
public class HashOpsTest {
    @Autowired
    private RedisTemplate<String, String> strRedisTemplate;
    @Autowired
    private RedisTemplate<String, Object> serializableRedisTemplate;

    public static BoundHashOperations<String, Object, Object> hashOperations = null;
    public static String key = "";

    @BeforeEach
    public void init() {
        key = "test_hash";
        hashOperations = strRedisTemplate.boundHashOps(key);
    }

    /**
     * 创建一个hash表，并存入键值对
     */
    @Test
    public void testHset() {
        hashOperations.put("id", "1");
        hashOperations.put("name", "redis");
        System.out.println(hashOperations.entries());
    }


    @Test
    public void testHashKeys() {
        Set<Object> keys = hashOperations.keys();
        System.out.println(keys);

    }

    @Test
    public void testHashPutGet() throws InterruptedException {
        hashOperations.put("1-1", "1");
        hashOperations.put("1-2", "1");
        Assertions.assertEquals("1", hashOperations.get("1-1"));
        TimeUnit.SECONDS.sleep(10);
        hashOperations.put("1-1", "0");
        Assertions.assertEquals("0", hashOperations.get("1-1"));
        System.out.println(strRedisTemplate.getExpire(key));
        System.out.println(strRedisTemplate.expire(key, 1000, TimeUnit.SECONDS));
    }

    @Test
    public void testMSetAndGet() {
        // 针对单个key
        Map<String, Object> map = new HashMap<>();
        map.put("2-1", "1");
        map.put("2-2", "1");
        hashOperations.putAll(map);
        Collection<Object> keys = Lists.list("2-1", "2-2");
        List<Object> objects = hashOperations.multiGet(keys);
        System.out.println(objects);

    }

    @Test
    public void testHashMGetAll() {
        Map<Object, Object> entries = hashOperations.entries();
        for (Map.Entry<Object, Object> entry : Objects.requireNonNull(entries).entrySet()) {
            Object k = entry.getKey();
            Object v = entry.getValue();
            System.out.println("key:" + k);
            System.out.println("value:" + v);
        }

    }

    @Test
    public void testMap() {

        //创建一个pojo对象
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setUserName("jack");
        user.setUserSex("man");
        Map<String, Object> map = new HashMap<>();
        //将pojo对象存入map中，这里需要将pojo对象序列化一下
        map.put("key1", user);
        //这里将Map写入redis数据库
        serializableRedisTemplate.opsForHash().putAll("map1", map);
    }
}
