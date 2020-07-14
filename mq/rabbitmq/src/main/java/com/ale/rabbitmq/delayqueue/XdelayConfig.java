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
     * The constant DELAY_QUEUE_ONE.
     */
    public static final String DELAY_QUEUE_ONE = "delay_queue_one";

    /**
     * The constant DELAY_QUEUE_TWO.
     */
    public static final String DELAY_QUEUE_TWO = "delay_queue_two";


    /**
     * Delay bindings declarables.
     *
     * @return the declarables
     */
    @Bean
    public Declarables delayBindings() {
        Queue delayQueueOne = QueueBuilder.durable(DELAY_QUEUE_ONE).build();
        Queue delayQueueTwo = QueueBuilder.durable(DELAY_QUEUE_TWO).build();
        DirectExchange delayExchange = ExchangeBuilder.directExchange(DELAY_EXCHANGE).delayed().durable(true).build();
        return new Declarables(
                delayQueueOne,
                delayQueueTwo,
                delayExchange,
                BindingBuilder.bind(delayQueueOne).to(delayExchange).withQueueName(),
                BindingBuilder.bind(delayQueueTwo).to(delayExchange).withQueueName());
    }
}
