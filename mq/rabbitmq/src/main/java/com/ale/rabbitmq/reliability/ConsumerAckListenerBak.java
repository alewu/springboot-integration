package com.ale.rabbitmq.reliability;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * @author alewu
 * @date 2020/7/15
 */
@Component
@Slf4j
public class ConsumerAckListenerBak {
    @RabbitListener(queues = TestQueueConfig.TEST_QUEUE)
    public void process(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        log.info("接收到消息：{}, tag:{}", message, tag);
        try {
            // 手工ACK,yml中设置 acknowledge-mode: manual（默认为自动确认）
            channel.basicAck(tag, true);
        } catch (IOException e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

}
