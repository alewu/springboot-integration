package com.ale.rabbitmq;

import com.ale.rabbitmq.model.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

import static com.ale.rabbitmq.constants.RabbitConstants.BINDING_PATTERN_IMPORTANT;
import static com.ale.rabbitmq.constants.RabbitConstants.TOPIC_EXCHANGE_NAME;
import static com.ale.rabbitmq.constants.RabbitConstants.TOPIC_QUEUE_1_NAME;

/**
 * @author alewu
 * @date 2020/5/20
 */
@Component
@EnableScheduling
public class MsgProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 给hello队列发送消息
     */
    @Scheduled(fixedRate = 10000)
    public void send() {
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setUserId(i);
            String msg = "hello, number: " + i;
            System.out.println("Producer, " + msg);
            rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_NAME, BINDING_PATTERN_IMPORTANT, user);
        }
    }
}
