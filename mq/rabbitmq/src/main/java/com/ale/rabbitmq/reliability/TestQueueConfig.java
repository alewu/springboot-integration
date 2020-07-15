package com.ale.rabbitmq.reliability;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
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

    @Bean
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        //开启手动 ack
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        // 预取大小(默认250)
        factory.setPrefetchCount(10);
        return factory;
    }

}
