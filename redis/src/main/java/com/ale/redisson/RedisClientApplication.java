package com.ale.redisson;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Instant;

/**
 * @author alewu
 * @date 2020/7/16
 */
@SpringBootApplication
@Slf4j
public class RedisClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisClientApplication.class, args);
    }

    @Bean
    public ApplicationRunner send(MsgProducer msgProducer) {
        return args -> {
            long start = Instant.now().toEpochMilli();
            for (int i = 0; i < 10; i++) {
                log.info("send msg: {}", i);
                DelayedJob delayedJob = new DelayedJob();
                delayedJob.setId(i);
                delayedJob.setName("jack");
                msgProducer.send(delayedJob);
            }
            log.info("takes {} ms", Instant.now().toEpochMilli() - start);
        };
    }

    //    @Bean
    public ApplicationRunner send1(MsgConsumer msgConsumer) {
        return args -> {
            long start = Instant.now().toEpochMilli();
            msgConsumer.recv();
            log.info("takes {} ms", Instant.now().toEpochMilli() - start);
        };
    }
}
