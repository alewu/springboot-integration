package com.ale.rabbitmq.demo;

import com.ale.rabbitmq.demo.producer.MessageProducer;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

/**
 * @author alewu
 * @date 2020/5/20
 */
@SpringBootApplication
public class DemoApplication {


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    @Order(1)
    public ApplicationRunner runner(MessageProducer messageProducer) {
        return args -> {
            messageProducer.sendMsg();
        };
    }

}
