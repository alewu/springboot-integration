package com.ale;

import com.ale.entity.UserEntity;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
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
    @Autowired
    @Qualifier("customExecutorPool")
    private ThreadPoolExecutor customExecutorPool;

    long start = 0;
    long end = 0;

    @BeforeEach
    public void init() {
        key = "test_hash";
        hashOperations = strRedisTemplate.boundHashOps(key);
        start = Instant.now().toEpochMilli();
    }

    @AfterEach
    public void after() {
        end = Instant.now().toEpochMilli();
        System.out.println(String.format("end - start = %d ms", end - start));
    }


    /**
     * 创建一个hash表，并存入键值对
     */
    @Test
    public void testHSet() {
        hashOperations.put("age", "10");
        hashOperations.put("id", "1");
        hashOperations.put("name", "redis");
        Set<Map.Entry<Object, Object>> entries = hashOperations.entries().entrySet();

    }

    /**
     * 创建一个hash表，并存入键值对
     */
    @Test
    public void testHGet() {
        String age = (String) hashOperations.get("age1");
        System.out.println(age);

    }

    @Test
    public void testHincr() {

        for (int i = 0; i < 100000; i++) {
            CompletableFuture.runAsync(() -> hashOperations.increment("id", 1),
                                       customExecutorPool);

        }
        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
    public void testHDel() {
        // 针对单个key
        Map<String, Object> map = new HashMap<>();
        map.put("1", "1");
        map.put("2", "1");
        map.put("3", "1");
        map.put("4", "1");
        hashOperations.putAll(map);
        //       Object[] keys = new Object[]{"3-1", "3-2"};
        //        List<Object> objects = hashOperations.multiGet(keys);
        //        System.out.println(objects);
        //        List<Integer> tgLinkRelationIds = new ArrayList<>();
        //        tgLinkRelationIds.add(1);
        //        tgLinkRelationIds.add(2);
        //        Object[] keys = tgLinkRelationIds.stream().map(String::valueOf).toArray(Object[]::new);
        //        hashOperations.delete(keys);
        System.out.println(hashOperations.keys().iterator().next());
    }

    @Test
    public void testMSetAndGetType() {
        // 针对单个key 不能存 Integer
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 0);
        map.put(2, 0);
        hashOperations.putAll(map);
        Collection<Object> keys = Lists.list(1, 2);
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
    public void testPipelined() {
        List<String> keys = Lists.list("test_hash", "test_hash2");
        List<Object> objects = strRedisTemplate.executePipelined(new RedisCallback<Object>() {
            final RedisSerializer<String> serializer = strRedisTemplate.getStringSerializer();

            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                for (String key : keys) {
                    connection.hashCommands().hGetAll(Objects.requireNonNull(serializer.serialize(key)));
                }
                return null;
            }
        });
        for (Object object : objects) {
            Map<String, Object> map = (Map<String, Object>) object;
            System.out.println(map.keySet());
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
