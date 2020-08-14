package com.ale.rabbitmq.demo.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static com.ale.rabbitmq.constants.RabbitConstants.MY_QUEUE;

@Slf4j
public class SimpleAmqpContainer {
    private final RabbitTemplate rabbitTemplate;

    public SimpleAmqpContainer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    @RabbitListener(queues = MY_QUEUE)
    public void processFailedMessages(Message message) {
        log.info("Received message: {}", message.toString());
    }

}
