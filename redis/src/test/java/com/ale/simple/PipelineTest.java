package com.ale.simple;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.List;
import java.util.Map;

@DataRedisTest
class PipelineTest {
    @Autowired
    private RedisTemplate<String, String> strRedisTemplate;

    @Test
    void testPipelined() {
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
}
