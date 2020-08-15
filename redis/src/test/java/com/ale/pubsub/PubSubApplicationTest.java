package com.ale.pubsub;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.Topic;

@SpringBootTest
class PubSubApplicationTest {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    @Qualifier("patternTopic")
    private Topic topic;

    @Test
    public void testRedisPubSub() {
        redisTemplate.convertAndSend(topic.getTopic(), "hello");
    }
}