package com.ale.rabbitmq.errorhandling.consumer;

import com.ale.rabbitmq.errorhandling.errorhandler.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static com.ale.rabbitmq.errorhandling.config.SimpleDLQAmqpConfig.EXCHANGE_MESSAGES;
import static com.ale.rabbitmq.errorhandling.config.SimpleDLQAmqpConfig.QUEUE_MESSAGES;
import static com.ale.rabbitmq.errorhandling.config.SimpleDLQAmqpConfig.QUEUE_MESSAGES_DLQ;

@Slf4j
public class RoutingDLQAmqpContainer {
    private final RabbitTemplate rabbitTemplate;

    public RoutingDLQAmqpContainer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = QUEUE_MESSAGES)
    public void receiveMessage(Message message) throws BusinessException {
        log.info("Received message: {}", message.toString());
        throw new BusinessException();
    }

    @RabbitListener(queues = QUEUE_MESSAGES_DLQ)
    public void processFailedMessagesRequeue(Message failedMessage) {
        log.info("Received failed message, requeueing: {}", failedMessage.toString());
        rabbitTemplate.send(EXCHANGE_MESSAGES, failedMessage.getMessageProperties().getReceivedRoutingKey(),
                            failedMessage);
    }
}
