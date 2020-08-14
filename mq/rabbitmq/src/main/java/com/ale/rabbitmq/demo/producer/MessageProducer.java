package com.ale.rabbitmq.demo.producer;

import com.ale.rabbitmq.exchangetype.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

import static com.ale.rabbitmq.constants.RabbitConstants.MY_QUEUE;


@Component
@Slf4j
public class MessageProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg() {
        long start = Instant.now().toEpochMilli();
        for (int i = 0; i < 100000; i++) {
            User user = new User();
            user.setUserId(i);
            user.setUserName("jack");
            CorrelationData correlationData = new CorrelationData("");
            rabbitTemplate.convertAndSend(MY_QUEUE, user, correlationData);
        }
        log.info("{} ms", Instant.now().toEpochMilli() - start);
    }


}
