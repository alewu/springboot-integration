package com.ale.rabbitmq.delayqueue.dlx;

import com.ale.rabbitmq.delayqueue.dlx.producer.MessageProducer;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author alewu
 * @date 2020/7/13
 */
@SpringBootApplication
public class DLQDelayQueueApplication {
    public static void main(String[] args) {
        SpringApplication.run(DLQDelayQueueApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner(MessageProducer messageProducer) {
        return args -> {
            messageProducer.sendMessage();
        };
    }

}
