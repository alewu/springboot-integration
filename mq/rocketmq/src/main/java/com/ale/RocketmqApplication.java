package com.ale;

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
public class RocketmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketmqApplication.class, args);
    }

    @Bean
    @Order(1)
    public ApplicationRunner runner(RocketMQProducer producer) {
        return args -> producer.sendMsg("hello word");
    }

}
