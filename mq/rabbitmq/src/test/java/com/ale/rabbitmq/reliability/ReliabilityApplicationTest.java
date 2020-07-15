package com.ale.rabbitmq.reliability;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static com.ale.rabbitmq.reliability.TestQueueConfig.TEST_QUEUE;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@Slf4j
class ReliabilityApplicationTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 交换机不存在
     */
    @Test
    void testPublisherConfirms() {

        // 设置消息发布确认功能，需要在yml中配置：publisher-confirms: simple
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                // 在yml中配置：publisher-confirms: none 时，该日志不打印
                log.info("ConfirmCallback -> 消息发布到交换器成功，id：{}", correlationData);
            } else {
                log.warn("ConfirmCallback -> 消息发布到交换器失败，错误原因为：{}", cause);
            }
        });
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        Object response = rabbitTemplate.convertSendAndReceive("EXCHANGE_NOT_EXIST", "ROUTING_KEY_NOT_EXIST", "rabbit"
                , correlationData);
        assertNull(response);
    }

    /**
     * 交换器存在，但路由key不存在，无法路由到队列， 测试Mandatory与ReturnCallback。
     */
    @Test
    void testPublisherReturns() {
        // 设置mandatory标志可以被认为是开启故障检测模式，它只会让RabbitMQ向你通知失败，而不会通知成功。如果消息路由正确，你的发布者将不会收到通知。
        // 设置不接受不可路由的消息，需要在yml中配置：publisher-returns: true
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            String correlationId = message.getMessageProperties().getCorrelationId();
            log.info("ReturnCallback -> 消息 {} 发送失败，应答码：{}，原因：{}，交换器: {}，路由键：{}",
                     correlationId,
                     replyCode,
                     replyText,
                     exchange,
                     routingKey);
        });
        // 使用此方法，当确认了所有的消费者都接收成功之后，才触发另一个,基于RPC
        Object response = rabbitTemplate.convertSendAndReceive(TestQueueConfig.TEST_EXCHANGE_NAME,
                                                               "ROUTING_KEY_NOT_EXIST", "rabbit");
        assertNull(response);

    }


    /**
     * 交换机存在，路由key不存在
     */
    @Test
    void testPublisherConfirmsNotPublisherReturns() {
        // 设置消息发布确认功能，需要在yml中配置：publisher-confirms: simple
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                log.info("ConfirmCallback -> 消息发布到交换器成功，id：{}", correlationData);
            } else {
                log.warn("ConfirmCallback -> 消息发布到交换器失败，错误原因为：{}", cause);
            }
        });
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            String correlationId = message.getMessageProperties().getCorrelationId();
            log.info("ReturnCallback -> 消息 {} 发送失败，应答码：{}，原因：{}，交换器: {}，路由键：{}",
                     correlationId,
                     replyCode,
                     replyText,
                     exchange,
                     routingKey);
        });
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        Object response = rabbitTemplate.convertSendAndReceive(TestQueueConfig.TEST_EXCHANGE_NAME,
                                                               "ROUTING_KEY_NOT_EXIST", "rabbit"
                , correlationData);
        assertNull(response);
    }


    /**
     * channel.basicNack() 可批量拒绝
     */
    @Test
    void testConsumerNAck() {
        rabbitTemplate.setReceiveTimeout(3L);
        Object nack = rabbitTemplate.convertSendAndReceive(TEST_QUEUE, "nack");
        assertNull(nack);
    }


    @Test
    void testConsumerAckRequeue() {
        rabbitTemplate.convertSendAndReceive(TEST_QUEUE, "nack-requeue");
    }

    /**
     * channel.basicReject()从协议层面上，reject是发送一个Basic.Reject响应，告知RabbitMQ无法对这条消息进行处理，
     * 当拒绝时，可以指定是否丢弃消息或使用requeue标志重新发送消息。当启用requeue时，RabbitMQ将会把这条消息重新放回到队列中。
     */
    @Test
    void testConsumerReject() {
        rabbitTemplate.convertSendAndReceive(TEST_QUEUE, "reject");
    }

    @Test
    void testConsumerRejectRequeue() {
        rabbitTemplate.convertSendAndReceive(TEST_QUEUE, "reject-requeue");
    }
}