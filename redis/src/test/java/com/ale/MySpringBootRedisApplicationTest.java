package com.ale;

import com.ale.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import java.util.BitSet;
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

    @Test
    public void testListRightPush() {
        BoundListOperations<String, String> listOperations = strRedisTemplate.boundListOps("test_list");
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

    @Test
    public void testHashPutGet() throws InterruptedException {
        String key = "test_hash";
        BoundHashOperations<String, Object, Object> hashOperations = strRedisTemplate.boundHashOps(key);
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
    public void testHash() {
        String key = "test_hash";
        BoundHashOperations<String, Object, Object> hashOperations = strRedisTemplate.boundHashOps(key);
        Set<Object> keys = hashOperations.keys();
        System.out.println(keys);

    }

    @Test
    public void testBitSet() {
        // Redis Setbit 命令用于对 key 所储存的字符串值，设置或清除指定偏移量上的位(bit)。
        String key = "test_bit_set";
        strRedisTemplate.expire(key, 1000, TimeUnit.SECONDS);
        for (int i = 0; i < 5; i++) {
            Boolean bit = strRedisTemplate.opsForValue().setBit(key, i, true);
            System.out.println("result" + i + " : " + bit);
        }
        for (int i = 0; i < 5; i++) {
            System.out.println("get" + i + " : " + strRedisTemplate.opsForValue().getBit(key, i));
        }
    }

    @Test
    public void testBitCount() {
        String key = "test_bit_set";

    }

    @Test
    public void testBitSetGetBytes() {
        String key = "test_bit_set";
        byte[] bytes = strRedisTemplate.opsForValue().get(key).getBytes();
        BitSet bitSet = fromByteArrayReverse(bytes);
        System.out.println(bitSet.isEmpty());
        strRedisTemplate.opsForValue().setBit(key, 1, false);
        System.out.println(bitSet.isEmpty());
    }

    public static BitSet fromByteArrayReverse(final byte[] bytes) {
        final BitSet bits = new BitSet();
        for (int i = 0; i < bytes.length * 8; i++) {
            if ((bytes[i / 8] & (1 << (7 - (i % 8)))) != 0) {
                bits.set(i);
            }
        }
        return bits;
    }

    public static byte[] toByteArrayReverse(final BitSet bits) {
        final byte[] bytes = new byte[bits.length() / 8 + 1];
        for (int i = 0; i < bits.length(); i++) {
            if (bits.get(i)) {
                final int value = bytes[i / 8] | (1 << (7 - (i % 8)));
                bytes[i / 8] = (byte) value;
            }
        }
        return bytes;
    }

    @Test
    public void testHyperLogLog() {

    }

}