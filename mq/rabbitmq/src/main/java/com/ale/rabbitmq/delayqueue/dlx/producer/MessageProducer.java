package com.ale.rabbitmq.delayqueue.dlx.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.ale.rabbitmq.delayqueue.dlx.config.DLQConfig.MESSAGE_QUEUE;
import static com.ale.rabbitmq.delayqueue.dlx.config.DLQConfig.NORMAL_EXCHANGE;

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
        log.info("send time: {}", LocalDateTime.now());
        rabbitTemplate.convertAndSend(NORMAL_EXCHANGE, MESSAGE_QUEUE, "Some message id:" + messageNumber++);
    }
}