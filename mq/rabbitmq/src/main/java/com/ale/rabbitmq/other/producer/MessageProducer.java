package com.ale.rabbitmq.other.producer;

import com.ale.rabbitmq.model.User;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

import static com.ale.rabbitmq.constants.RabbitConstants.*;

/**
 * @author alewu
 * @date 2020/6/29
 */
@Component
public class MessageProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg() {
        long start = Instant.now().toEpochMilli();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setUserId(i);
            user.setUserName("jack");
            CorrelationData correlationData = new CorrelationData("");
            rabbitTemplate.convertAndSend(MY_QUEUE, user, correlationData);
        }
        System.out.println(Instant.now().toEpochMilli() - start + " ms");
    }


    public void sendFanoutMsg() {
        long start = Instant.now().toEpochMilli();
        for (int i = 0; i < 100000; i++) {
            User user = new User();
            user.setUserId(i);
            rabbitTemplate.convertAndSend(FANOUT_EXCHANGE_NAME, "", user);
        }
        System.out.println(Instant.now().toEpochMilli() - start + " ms");
    }

    public void sendTopicMsg() {
        long start = Instant.now().toEpochMilli();
        for (int i = 0; i < 100000; i++) {
            User user = new User();
            user.setUserId(i);
            rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_NAME, ROUTING_KEY_USER_IMPORTANT_WARN, "topic important " +
                    "warn：" + user);
            rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_NAME,
                                          ROUTING_KEY_USER_IMPORTANT_ERROR, "topic important error：" + user);
        }
        System.out.println(Instant.now().toEpochMilli() - start + " ms");
    }


}
