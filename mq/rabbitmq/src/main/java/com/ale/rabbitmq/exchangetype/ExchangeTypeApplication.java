package com.ale.rabbitmq.exchangetype;

import com.ale.rabbitmq.exchangetype.producer.DirectQueueProducer;
import com.ale.rabbitmq.exchangetype.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private MessageProducer messageProducer;

    public static void main(String[] args) {
        SpringApplication.run(ExchangeTypeApplication.class, args);
    }

    @Bean
    @Order(1)
    public ApplicationRunner runner(DirectQueueProducer directQueueProducer) {
        return args -> {
            directQueueProducer.sendMsg();
            messageProducer.sendFanoutMsg();
            messageProducer.sendTopicMsg();
        };
    }

}
