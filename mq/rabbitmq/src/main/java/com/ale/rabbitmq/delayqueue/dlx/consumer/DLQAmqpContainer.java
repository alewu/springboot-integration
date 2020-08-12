package com.ale.rabbitmq.delayqueue.dlx.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static com.ale.rabbitmq.delayqueue.dlx.config.DLQConfig.DEAD_LETTER_QUEUE;

@Slf4j
public class DLQAmqpContainer {
    private final RabbitTemplate rabbitTemplate;

    public DLQAmqpContainer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = DEAD_LETTER_QUEUE)
    public void processFailedMessages(Message message) {
        log.info("Received failed message: {}", message.toString());
    }


}
