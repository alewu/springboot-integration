package com.ale.pubsub;

import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author alewu
 * @date 2020/8/15
 */
@SpringBootApplication(exclude = RedissonAutoConfiguration.class)
public class PubSubApplication {
    public static void main(String[] args) {
        SpringApplication.run(PubSubApplication.class, args);
    }
}
