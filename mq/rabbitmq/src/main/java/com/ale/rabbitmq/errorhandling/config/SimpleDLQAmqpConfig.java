package com.ale.rabbitmq.errorhandling.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author alewu
 * @date 2020/7/7
 */
@Configuration
public class SimpleDLQAmqpConfig {
    public static final String QUEUE_MESSAGES = "baeldung-messages-queue";
    public static final String QUEUE_MESSAGES_DLQ = QUEUE_MESSAGES + ".dlq";
    public static final String EXCHANGE_MESSAGES = "baeldung-messages-exchange";
    public static final String DLX_EXCHANGE_MESSAGES = QUEUE_MESSAGES + ".dlx";

    @Bean
    Queue messagesQueue() {
        return QueueBuilder.durable(QUEUE_MESSAGES)
                           // DLX，dead letter发送到的exchange
                           .withArgument("x-dead-letter-exchange", DLX_EXCHANGE_MESSAGES)
                           .build();
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_MESSAGES);
    }

    @Bean
    Queue deadLetterQueue() {
        return QueueBuilder.durable(QUEUE_MESSAGES_DLQ).build();
    }

    @Bean
    FanoutExchange deadLetterExchange() {
        return new FanoutExchange(DLX_EXCHANGE_MESSAGES);
    }

    @Bean
    public Declarables directBindings() {
        return new Declarables(
                messagesQueue(),
                BindingBuilder.bind(messagesQueue()).to(directExchange()).withQueueName());
    }

    @Bean
    public Declarables deadLetterBindings() {
        return new Declarables(
                deadLetterQueue(),
                BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()));
    }
}
