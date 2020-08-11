package com.ale.rabbitmq.delayqueue.dlx.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import static com.ale.rabbitmq.delayqueue.dlx.config.DLQConfig.REDIRECT_QUEUE;

@Slf4j
public class SimpleDLQAmqpContainer {

    @RabbitListener(queues = REDIRECT_QUEUE)
    public void processFailedMessages(Message message) {
        log.info("Received failed message: {}", message.toString());
    }


}
