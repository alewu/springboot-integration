package com.ale.rabbitmq;

import com.ale.rabbitmq.model.User;
import com.ale.rabbitmq.service.AsyncTaskService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Instant;

import static com.ale.rabbitmq.constants.RabbitConstants.FANOUT_QUEUE_1_NAME;
import static com.ale.rabbitmq.constants.RabbitConstants.FANOUT_QUEUE_2_NAME;
import static com.ale.rabbitmq.constants.RabbitConstants.MY_QUEUE;

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
    public ApplicationRunner runner(RabbitTemplate rabbitTemplate) {
        String message = " payload is broadcast";
        return args -> {
            long start = Instant.now().toEpochMilli();
            for (int i = 0; i < 100000; i++) {
//                User user = new User();
//                user.setUserId(i);
//                rabbitTemplate.convertAndSend(MY_QUEUE, user);
                //               rabbitTemplate.convertAndSend(FANOUT_EXCHANGE_NAME, "", "fanout：" + message + i);
                //                rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_NAME, ROUTING_KEY_USER_IMPORTANT_WARN,
                //                "topic important warn：" + message + i);
                //                rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_NAME,
                //                ROUTING_KEY_USER_IMPORTANT_ERROR, "topic important error：" + message + i);
            }
            System.out.println(Instant.now().toEpochMilli() - start + " ms");
        };
    }

}
