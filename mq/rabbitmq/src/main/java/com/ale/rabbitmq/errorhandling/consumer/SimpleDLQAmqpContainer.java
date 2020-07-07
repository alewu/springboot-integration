package com.ale.rabbitmq.errorhandling.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static com.ale.rabbitmq.errorhandling.config.SimpleDLQAmqpConfig.EXCHANGE_MESSAGES;
import static com.ale.rabbitmq.errorhandling.config.SimpleDLQAmqpConfig.QUEUE_MESSAGES_DLQ;

@Slf4j
public class SimpleDLQAmqpContainer {
    private final RabbitTemplate rabbitTemplate;

    public SimpleDLQAmqpContainer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    @RabbitListener(queues = QUEUE_MESSAGES_DLQ)
    public void processFailedMessages(Message message) {
        log.info("Received failed message: {}", message.toString());
    }

    @RabbitListener(queues = QUEUE_MESSAGES_DLQ)
    public void processFailedMessagesRequeue(Message failedMessage) {
        log.info("Received failed message, requeueing: {}", failedMessage.toString());
        log.info("Received failed message, requeueing: {}",
                 failedMessage.getMessageProperties().getReceivedRoutingKey());
        rabbitTemplate.send(EXCHANGE_MESSAGES, failedMessage.getMessageProperties().getReceivedRoutingKey(),
                            failedMessage);
    }

}
