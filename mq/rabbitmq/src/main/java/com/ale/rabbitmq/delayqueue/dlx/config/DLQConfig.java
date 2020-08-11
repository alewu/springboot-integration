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
    public static final String REDIRECT_QUEUE = "redirect-queue";


    @Bean
    public Declarables delayBindings() {
        DirectExchange normalExchange = ExchangeBuilder.directExchange(NORMAL_EXCHANGE).durable(true).build();
        DirectExchange deadLetterExchange = ExchangeBuilder.directExchange(DLX_EXCHANGE).durable(true).build();
        // 声明一个死信队列. x-dead-letter-exchange 对应 死信交换机 x-dead-letter-routing-key 对应
        Queue deadLetterQueue = QueueBuilder.durable(DEAD_LETTER_QUEUE)
                                            .withArgument("x-dead-letter-exchange", DLX_EXCHANGE)
                                            //                                          .withArgument
                                            //                                          ("x-dead-letter-routing-key",
                                            //                                          "KEY_R")
                                            .build();
        Queue redirectQueue = QueueBuilder.durable(REDIRECT_QUEUE).build();

        return new Declarables(
                deadLetterQueue,
                redirectQueue,
                normalExchange,
                deadLetterExchange,
                BindingBuilder.bind(redirectQueue).to(normalExchange).with(DEAD_LETTER_QUEUE),
                BindingBuilder.bind(deadLetterQueue).to(deadLetterExchange).withQueueName());
    }

}
