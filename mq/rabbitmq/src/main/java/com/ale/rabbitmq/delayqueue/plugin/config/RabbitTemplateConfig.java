package com.ale.rabbitmq.delayqueue.plugin.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

/**
 * @author alewu
 * @date 2020/8/11
 */
@Configuration
@Slf4j
public class RabbitTemplateConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setConfirmCallback(((correlationData, ack, cause) -> {
            // 设置消息发布确认功能，需要在yml中配置：publisher-confirms: simple
            if (ack) {
                // 在yml中配置：publisher-confirms: none 时，该日志不打印
                log.info("ConfirmCallback -> 消息发布到交换器成功，id：{}",
                         Optional.ofNullable(correlationData).map(CorrelationData::getId).orElse("null"));
            } else {
                log.warn("ConfirmCallback -> 消息发布到交换器失败，错误原因为：{}", cause);
            }
        }));
        rabbitTemplate.setReturnCallback(((message, replyCode, replyText, exchange, routingKey) -> {
            String correlationId = message.getMessageProperties().getCorrelationId();
            log.info("ReturnCallback -> 消息 {} 发送失败，应答码：{}，原因：{}，交换器: {}，路由键：{}",
                     correlationId,
                     replyCode,
                     replyText,
                     exchange,
                     routingKey);
        }));
        return rabbitTemplate;
    }

}
