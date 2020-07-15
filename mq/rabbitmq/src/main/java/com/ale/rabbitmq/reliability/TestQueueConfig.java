package com.ale.rabbitmq.reliability;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息传播配置
 *
 * @author alewu
 * @date 2020/5/20
 */
@Configuration
public class TestQueueConfig {

    public static final String TEST_QUEUE = "testQueue";

    public static final String TEST_EXCHANGE_NAME = "test_exchange_name";

    @Bean
    public Declarables directBindings() {
        Queue testDirectQueue = QueueBuilder.durable(TEST_QUEUE).build();
        DirectExchange testDirectExchange = ExchangeBuilder.directExchange(TEST_EXCHANGE_NAME).durable(true).build();
        return new Declarables(
                testDirectQueue,
                testDirectExchange,
                BindingBuilder.bind(testDirectQueue).to(testDirectExchange).withQueueName());
    }


}
