package com.ale.rabbitmq.errorhandling.producer;

import com.ale.rabbitmq.errorhandling.config.SimpleDLQAmqpConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * @author alewu
 * @date 2020/7/7
 */
@Service
public class MessageProducer {

    private static final Logger log = LoggerFactory.getLogger(MessageProducer.class);
    private int messageNumber = 0;
    private final RabbitTemplate rabbitTemplate;

    public MessageProducer(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage() {
        log.info("Sending message...");
        rabbitTemplate.convertAndSend(SimpleDLQAmqpConfig.EXCHANGE_MESSAGES, SimpleDLQAmqpConfig.QUEUE_MESSAGES,
                                      "Some message id:" + messageNumber++);
    }
}