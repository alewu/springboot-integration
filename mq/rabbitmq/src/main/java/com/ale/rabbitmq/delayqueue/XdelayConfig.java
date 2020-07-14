package com.ale.rabbitmq.delayqueue;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Xdelay config.
 *
 * @author alewu
 * @date 2020 /7/13
 */
@Configuration
@EnableRabbit
public class XdelayConfig {

    /**
     * The constant DELAY_EXCHANGE.
     */
    public static final String DELAY_EXCHANGE = "delay_exchange";
    /**
     * The constant DELAY_KEY.
     */
    public static final String DELAY_KEY = "delay_key";
    /**
     * The constant DELAY_QUEUE.
     */
    public static final String DELAY_QUEUE = "delay_queue";


    @Bean
    public Declarables delayBindings() {
        Queue delayQueue = QueueBuilder.durable(DELAY_QUEUE).build();
        DirectExchange delayExchange = ExchangeBuilder.directExchange(DELAY_EXCHANGE).delayed().durable(true).build();
        return new Declarables(
                delayQueue,
                delayExchange,
                BindingBuilder.bind(delayQueue).to(delayExchange).withQueueName());
    }
}
