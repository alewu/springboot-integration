package com.ale.redisson;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

    //    @Bean
    //    public ApplicationRunner send(MsgProducer msgProducer) {
    //        return args -> {
    //            long start = Instant.now().toEpochMilli();
    //            for (int i = 0; i < 1000; i++) {
    //                log.info("send msg: {}", i);
    //               msgProducer.send("" + i);
    //            }
    //            log.info("takes {} ms", Instant.now().toEpochMilli() - start);
    //        };
    //    }
}
