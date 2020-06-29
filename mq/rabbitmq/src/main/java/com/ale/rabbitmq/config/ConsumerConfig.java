package com.ale.rabbitmq.config;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author alewu
 * @date 2020/6/29
 */
@Configuration
public class ConsumerConfig {
    @Autowired
    private ConnectionFactory connectionFactory;

    AtomicInteger count = new AtomicInteger();

    @Bean
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory() {
        //SimpleRabbitListenerContainerFactory发现消息中有content_type有text就会默认将其转换成string类型的
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setConsumerTagStrategy(queue -> "msg" + (count.incrementAndGet()));
        factory.setConcurrentConsumers(2);
        return factory;
    }

    @Bean
    public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //        container.setQueueNames("myQueue");
        //设置消费者的consumerTag_tag
        container.setConsumerTagStrategy(queue -> "order_queue_" + (count.incrementAndGet()));
        //设置消费者的Arguments
        Map<String, Object> args = new HashMap<>();
        args.put("module", "订单模块");
        args.put("fun", "发送消息");
        container.setConsumerArguments(args);
        container.setMessageListener(message -> {
            System.out.println("====接收到消息=====");
            System.out.println(message.getMessageProperties());
        });
        return container;
    }

}
