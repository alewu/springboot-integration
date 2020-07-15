package com.ale.rabbitmq.other.producer;

import com.ale.rabbitmq.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

import static com.ale.rabbitmq.constants.RabbitConstants.HIGH_QUEUE;
import static com.ale.rabbitmq.constants.RabbitConstants.MIDDLE_QUEUE;
import static com.ale.rabbitmq.constants.RabbitConstants.SLOW_QUEUE;

/**
 * @author alewu
 * @date 2020/6/29
 */
@Component
@Slf4j
public class DirectQueueProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void sendMsg() {
        long start = Instant.now().toEpochMilli();
        for (int i = 0; i < 1000; i++) {
            User user = new User();
            user.setUserId(i);
            user.setUserName("jack");
            if (i % 2 == 0) {
                rabbitTemplate.convertAndSend(SLOW_QUEUE, user);
            } else if (i % 3 == 0) {
                rabbitTemplate.convertAndSend(MIDDLE_QUEUE, user);
            } else {
                rabbitTemplate.convertAndSend(HIGH_QUEUE, user);
            }

        }

        log.info("takes {} ms", Instant.now().toEpochMilli() - start);
    }

}
