package com.ale.rabbitmq;

import com.ale.rabbitmq.exchangetype.producer.DirectQueueProducer;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

/**
  *
  * @author alewu
  * @date 2020/5/20
  */
@SpringBootApplication
public class RabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqApplication.class, args);
    }

    @Bean
    @Order(1)
    public ApplicationRunner runner(DirectQueueProducer directQueueProducer) {
        return args -> directQueueProducer.sendMsg();
    }

}
