package com.ale.redis.pubsub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@EnableScheduling
@Component
public class MsgSender {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 间隔2秒，通过stringRedisTemplate对象向redis消息队列chat频道发布消息
     */
    @Scheduled(fixedDelay = 5000)
    public void sendPatternMessage() {
        stringRedisTemplate.convertAndSend("log.error", "error " + new Random().nextInt(100));
        stringRedisTemplate.convertAndSend("log.warn", "warn " + new Random().nextInt(100));
    }

    /**
     * 间隔2秒，通过stringRedisTemplate对象向redis消息队列chat频道发布消息
     */
    @Scheduled(fixedDelay = 10000)
    public void sendChannelMessage() {
        stringRedisTemplate.convertAndSend("news", "news " + new Random().nextInt(100));
    }
}
