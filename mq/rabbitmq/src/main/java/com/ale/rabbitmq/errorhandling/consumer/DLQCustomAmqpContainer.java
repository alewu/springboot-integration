package com.ale.rabbitmq.errorhandling.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static com.ale.rabbitmq.errorhandling.config.SimpleDLQAmqpConfig.EXCHANGE_MESSAGES;
import static com.ale.rabbitmq.errorhandling.config.SimpleDLQAmqpConfig.QUEUE_MESSAGES_DLQ;
import static com.ale.rabbitmq.errorhandling.consumer.MessagesConsumer.HEADER_X_RETRIES_COUNT;

@Slf4j
public class DLQCustomAmqpContainer {
    private final RabbitTemplate rabbitTemplate;
    public static final int MAX_RETRIES_COUNT = 2;

    public DLQCustomAmqpContainer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = QUEUE_MESSAGES_DLQ)
    public void processFailedMessagesRetryHeaders(Message failedMessage) {
        Integer retriesCnt = (Integer) failedMessage.getMessageProperties().getHeaders().get(HEADER_X_RETRIES_COUNT);
        if (retriesCnt == null) {
            retriesCnt = 1;
        }
        if (retriesCnt > MAX_RETRIES_COUNT) {
            log.info("Discarding message");
            return;
        }
        log.info("Retrying message for the {} time", retriesCnt);
        failedMessage.getMessageProperties().getHeaders().put(HEADER_X_RETRIES_COUNT, ++retriesCnt);
        rabbitTemplate.send(EXCHANGE_MESSAGES, failedMessage.getMessageProperties().getReceivedRoutingKey(),
                            failedMessage);
    }
}
