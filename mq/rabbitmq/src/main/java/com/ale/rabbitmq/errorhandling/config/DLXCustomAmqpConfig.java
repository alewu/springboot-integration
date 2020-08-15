package com.ale.rabbitmq.errorhandling.config;

import org.springframework.amqp.core.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.ale.rabbitmq.errorhandling.config.SimpleDLQAmqpConfig.DLX_EXCHANGE_MESSAGES;
import static com.ale.rabbitmq.errorhandling.config.SimpleDLQAmqpConfig.EXCHANGE_MESSAGES;
import static com.ale.rabbitmq.errorhandling.config.SimpleDLQAmqpConfig.QUEUE_MESSAGES;
import static com.ale.rabbitmq.errorhandling.config.SimpleDLQAmqpConfig.QUEUE_MESSAGES_DLQ;

@Configuration
@ConditionalOnProperty(
        value = "amqp.configuration.current",
        havingValue = "dlx-custom")
public class DLXCustomAmqpConfig {
    @Bean
    Queue messagesQueue() {
        return QueueBuilder.durable(QUEUE_MESSAGES)
                           .withArgument("x-dead-letter-exchange", DLX_EXCHANGE_MESSAGES)
                           .build();
    }

    @Bean
    FanoutExchange deadLetterExchange() {
        return new FanoutExchange(DLX_EXCHANGE_MESSAGES);
    }

    @Bean
    Queue deadLetterQueue() {
        return QueueBuilder.durable(QUEUE_MESSAGES_DLQ).build();
    }

    @Bean
    Binding deadLetterBinding() {
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange());
    }

    @Bean
    DirectExchange messagesExchange() {
        return new DirectExchange(EXCHANGE_MESSAGES);
    }

    @Bean
    Binding bindingMessages() {
        return BindingBuilder.bind(messagesQueue()).to(messagesExchange()).with(QUEUE_MESSAGES);
    }
}
