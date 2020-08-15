package com.ale.rabbitmq.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.ale.rabbitmq.constants.RabbitConstants.DIRECT_EXCHANGE_NAME;
import static com.ale.rabbitmq.constants.RabbitConstants.MY_QUEUE;

@Configuration
public class QueueConfig {

    @Bean
    public Declarables directBindings() {
        Queue myQueue = QueueBuilder.durable(MY_QUEUE).build();
        DirectExchange directExchange = ExchangeBuilder.directExchange(DIRECT_EXCHANGE_NAME).durable(true).build();
        return new Declarables(
                myQueue,
                directExchange,
                BindingBuilder.bind(myQueue).to(directExchange).withQueueName());
    }

}
