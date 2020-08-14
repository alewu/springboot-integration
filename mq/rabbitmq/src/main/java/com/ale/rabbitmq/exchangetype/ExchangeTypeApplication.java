package com.ale.rabbitmq.exchangetype;

import com.ale.rabbitmq.exchangetype.producer.DirectQueueProducer;
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
public class ExchangeTypeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExchangeTypeApplication.class, args);
    }

    @Bean
    @Order(1)
    public ApplicationRunner runner(DirectQueueProducer directQueueProducer) {
        return args -> directQueueProducer.sendMsg();
    }

}
