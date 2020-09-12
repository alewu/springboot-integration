package com.ale.simple;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.util.StopWatch;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Slf4j
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
    void testSet() {
        for (int i = 0; i < 100000; i++) {
            strRedisTemplate.opsForValue().set("user:" + i, i + "");
        }
    }

    @Test
    void testScan() {
        long start = System.currentTimeMillis();
        //需要匹配的key
        String patternKey = "user:*";
        ScanOptions scanOptions = new ScanOptions.ScanOptionsBuilder().match(patternKey).count(1000).build();
        Set<String> allUsers = strRedisTemplate.execute(new RedisCallback<Set<String>>() {
            @Override
            public Set<String> doInRedis(RedisConnection connection) throws DataAccessException {
                Set<String> partUsers = new HashSet<>();
                // 切记这里一定要关闭，否则会耗尽连接数
                try (Cursor<byte[]> cursor = connection.scan(scanOptions)) {

                    while (cursor.hasNext()) {
                        partUsers.add(new String(cursor.next()));
                    }

                } catch (IOException e) {
                    log.error("getTotalUsers cursor close", e);
                }

                return partUsers;
            }
        });
        log.info("scan扫描共耗时：{} ms key数量：{}", System.currentTimeMillis() - start,
                 Optional.ofNullable(allUsers).orElse(Collections.emptySet()).size());
        Assertions.assertNotNull(allUsers);

    }

    @Test
    void testSSet() {
        for (int i = 0; i < 10000; i++) {
            strRedisTemplate.opsForSet().add("student", "user:" + i + "");
        }
    }

    @Test
    void testSScan() {
        long start = System.currentTimeMillis();
        String patternKey = "user:*";
        ScanOptions scanOptions = new ScanOptions.ScanOptionsBuilder().match(patternKey).count(1000).build();
        Set<String> allUsers = new HashSet<>();
        try (Cursor<String> cursor = strRedisTemplate.opsForSet().scan("student", scanOptions)) {
            while (cursor.hasNext()) {
                String next = cursor.next();
                allUsers.add(next);
            }
        } catch (IOException e) {
            log.error("getTotalUsers cursor close", e);
        }
        log.info("scan扫描共耗时：{} ms key数量：{}", System.currentTimeMillis() - start,
                 allUsers.size());
        Assertions.assertNotNull(allUsers);
    }

    @Test
    void testHScan() {
        long start = System.currentTimeMillis();
        String patternKey = "user:*";
        ScanOptions scanOptions = new ScanOptions.ScanOptionsBuilder().match(patternKey).count(1000).build();
        Set<String> allUsers = new HashSet<>();
        final HashOperations<String, String, String> hashOperations = strRedisTemplate.opsForHash();

        try (Cursor<Map.Entry<String, String>> entryCursor = hashOperations.scan("student", scanOptions)) {
            while (entryCursor.hasNext()) {
                final Map.Entry<String, String> next = entryCursor.next();
                final String key = next.getKey();
                final String value = next.getValue();
            }
        } catch (IOException e) {
            log.error("getTotalUsers cursor close", e);
        }
        log.info("scan扫描共耗时：{} ms key数量：{}", System.currentTimeMillis() - start,
                 allUsers.size());
        Assertions.assertNotNull(allUsers);
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
