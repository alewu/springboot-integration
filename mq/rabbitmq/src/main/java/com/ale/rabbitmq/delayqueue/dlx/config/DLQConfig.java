package com.ale.rabbitmq.delayqueue.dlx.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * The type Dlq config.
 */
@Configuration
@EnableRabbit
public class DLQConfig {
    /**
     * The constant DEAD_LETTER_QUEUE.
     */
    public static final String DEAD_LETTER_QUEUE = "dead-letter-queue";
    /**
     * The constant DLX_EXCHANGE.
     */
    public static final String DLX_EXCHANGE = DEAD_LETTER_QUEUE + ".dlx";

    /**
     * The constant DLX_EXCHANGE.
     */
    public static final String NORMAL_EXCHANGE = "normalExchange";
    /**
     * The constant REDIRECT_QUEUE.
     */
    public static final String MESSAGE_QUEUE = "message-queue";


    @Bean
    public Declarables directBindings() {
        Queue messageQueue = QueueBuilder.durable(MESSAGE_QUEUE)
                                         .ttl(1000)
                                         .withArgument("x-dead-letter-exchange", DLX_EXCHANGE)
                                         //                                          .withArgument
                                         //                                          ("x-dead-letter-routing-key",
                                         //                                          "KEY_R")
                                         .build();
        DirectExchange normalExchange = ExchangeBuilder.directExchange(NORMAL_EXCHANGE).durable(true).build();
        return new Declarables(
                messageQueue,
                normalExchange,
                BindingBuilder.bind(messageQueue).to(normalExchange).withQueueName());
    }

    @Bean
    public Declarables deadLetterBindings() {
        Queue deadLetterQueue = QueueBuilder.durable(DEAD_LETTER_QUEUE).build();
        FanoutExchange deadLetterExchange = ExchangeBuilder.fanoutExchange(DLX_EXCHANGE).durable(true).build();
        return new Declarables(
                deadLetterQueue,
                deadLetterExchange,
                BindingBuilder.bind(deadLetterQueue).to(deadLetterExchange));
    }
}
