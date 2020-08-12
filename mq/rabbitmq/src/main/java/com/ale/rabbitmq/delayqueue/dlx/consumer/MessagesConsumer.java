package com.ale.rabbitmq.delayqueue.dlx.consumer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagesConsumer {

    private final RabbitTemplate rabbitTemplate;

    public MessagesConsumer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Bean
    @ConditionalOnProperty(value = "amqp.configuration.current", havingValue = "simple-dlq")
    public DLQAmqpContainer simpleAmqpContainer() {
        return new DLQAmqpContainer(rabbitTemplate);
    }

}