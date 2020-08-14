package com.ale.rabbitmq.demo.consumer;

import com.ale.rabbitmq.errorhandling.consumer.SimpleDLQAmqpContainer;
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
    public com.ale.rabbitmq.errorhandling.consumer.SimpleDLQAmqpContainer simpleAmqpContainer() {
        return new SimpleDLQAmqpContainer(rabbitTemplate);
    }

}