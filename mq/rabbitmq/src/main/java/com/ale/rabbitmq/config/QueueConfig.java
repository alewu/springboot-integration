package com.ale.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.ale.rabbitmq.constants.RabbitConstants.*;

/**
 * 消息传播配置
 *
 * @author alewu
 * @date 2020/5/20
 */
@Configuration
public class QueueConfig {

    @Bean
    public Declarables directBindings() {
        Queue slowDirectQueue = QueueBuilder.durable(SLOW_QUEUE).build();
        Queue middleDirectQueue = QueueBuilder.durable(MIDDLE_QUEUE).build();
        Queue highDirectQueue = QueueBuilder.durable(HIGH_QUEUE).build();
        DirectExchange directExchange = ExchangeBuilder.directExchange(DIRECT_EXCHANGE_NAME).durable(true).build();
        return new Declarables(
                slowDirectQueue,
                middleDirectQueue,
                highDirectQueue,
                directExchange,
                BindingBuilder.bind(slowDirectQueue).to(directExchange).withQueueName(),
                BindingBuilder.bind(middleDirectQueue).to(directExchange).withQueueName(),
                BindingBuilder.bind(highDirectQueue).to(directExchange).withQueueName());
    }


    @Bean
    public Declarables fanoutBindings() {
        Queue fanoutQueue1 = new Queue(FANOUT_QUEUE_1_NAME, NON_DURABLE);
        Queue fanoutQueue2 = new Queue(FANOUT_QUEUE_2_NAME, NON_DURABLE);
        FanoutExchange fanoutExchange = new FanoutExchange(FANOUT_EXCHANGE_NAME, NON_DURABLE, NON_DURABLE);

        return new Declarables(
                fanoutQueue1,
                fanoutQueue2,
                fanoutExchange,
                BindingBuilder.bind(fanoutQueue1).to(fanoutExchange),
                BindingBuilder.bind(fanoutQueue2).to(fanoutExchange));
    }

    @Bean
    public Declarables topicBindings() {
        Queue topicQueue1 = new Queue(TOPIC_QUEUE_1_NAME, NON_DURABLE);
        Queue topicQueue2 = new Queue(TOPIC_QUEUE_2_NAME, NON_DURABLE);

        TopicExchange topicExchange = new TopicExchange(TOPIC_EXCHANGE_NAME, NON_DURABLE, NON_DURABLE);

        return new Declarables(
                topicQueue1,
                topicQueue2,
                topicExchange,
                BindingBuilder
                        .bind(topicQueue1)
                        .to(topicExchange).with(BINDING_PATTERN_IMPORTANT),
                BindingBuilder
                        .bind(topicQueue2)
                        .to(topicExchange).with(BINDING_PATTERN_ERROR));
    }
}
