package com.ale.rabbitmq;

import com.ale.rabbitmq.producer.MessageProducer;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
    public ApplicationRunner runner(MessageProducer messageProducer) {
        return args -> messageProducer.sendMsg();
    }

}
