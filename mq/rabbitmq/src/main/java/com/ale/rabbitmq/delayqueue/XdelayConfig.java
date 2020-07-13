package com.ale.rabbitmq.delayqueue;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

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

    /**
     * 延时队列交换机
     * 注意这里的交换机类型：CustomExchange
     *
     * @return custom exchange
     */
    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(DELAY_EXCHANGE, "x-delayed-message", true, false, args);
    }

    /**
     * 延时队列
     *
     * @return queue queue
     */
    @Bean
    public Queue delayQueue() {
        return new Queue(DELAY_QUEUE, true);
    }

    /**
     * 给延时队列绑定交换机
     *
     * @param cfgDelayQueue        the cfg delay queue
     * @param cfgUserDelayExchange the cfg user delay exchange
     * @return binding binding
     */
    @Bean
    public Binding cfgDelayBinding(Queue cfgDelayQueue, CustomExchange cfgUserDelayExchange) {
        return BindingBuilder.bind(cfgDelayQueue).to(cfgUserDelayExchange).with(DELAY_KEY).noargs();
    }
}
