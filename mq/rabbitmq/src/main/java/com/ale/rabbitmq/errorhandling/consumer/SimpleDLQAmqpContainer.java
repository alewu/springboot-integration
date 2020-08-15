package com.ale.rabbitmq.errorhandling.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static com.ale.rabbitmq.constants.RabbitConstants.MY_QUEUE;

@Slf4j
public class SimpleDLQAmqpContainer {
    private final RabbitTemplate rabbitTemplate;

    public SimpleDLQAmqpContainer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    @RabbitListener(queues = MY_QUEUE)
    public void processFailedMessages(Message message) {
        log.info("Received failed message: {}", message.toString());
    }


}
