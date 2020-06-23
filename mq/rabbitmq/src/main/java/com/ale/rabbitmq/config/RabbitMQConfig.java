package com.ale.rabbitmq.config;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author alewu
 * @date 2018/7/5 11:18
 */
@Configuration
@EnableRabbit
public class RabbitMQConfig {
    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    public MessageConverter messageConverter() {
        ContentTypeDelegatingMessageConverter messageConverter = new ContentTypeDelegatingMessageConverter();
        messageConverter.addDelegate("application/json", new Jackson2JsonMessageConverter());
        return messageConverter;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setPrefetchCount(100);
        factory.setConnectionFactory(connectionFactory);
        // 设置并发数
        int concurrency = Runtime.getRuntime().availableProcessors();
        factory.setConcurrentConsumers(2);
//        factory.setMaxConcurrentConsumers(concurrency + 10);
        factory.setMessageConverter(messageConverter());
        return factory;
    }


}
