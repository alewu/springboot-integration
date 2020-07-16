package com.ale.base;

import com.ale.entity.UserEntity;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.BitSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class MySpringBootRedisApplicationTest {
    @Autowired
    private RedisTemplate<String, String> strRedisTemplate;
    @Autowired
    private RedisTemplate<String, Object> serializableRedisTemplate;



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
    public void testDel() {
        String key = "qr_code*";
        Set<String> keys = strRedisTemplate.keys(key);
        if (keys != null) {
            strRedisTemplate.delete(keys);
        }
    }


    @Test
    public void testPipelined() {
        List<String> keys = Lists.list("test_hash", "test_hash2");
        strRedisTemplate.executePipelined(new RedisCallback<Object>() {
            RedisSerializer<String> serializer = strRedisTemplate.getStringSerializer();

            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                for (String key : keys) {
                    Map<byte[], byte[]> map = connection.hashCommands().hGetAll(serializer.serialize(key));
                }
                return null;
            }
        });

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