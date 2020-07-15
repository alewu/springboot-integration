package com.ale.rabbitmq.reliability;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
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
@RabbitListener(queues = TestQueueConfig.TEST_QUEUE)
@Slf4j
public class ConsumerAckListener {
    @RabbitHandler
    public void process(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        log.info("接收到消息：{}, tag:{}", message, tag);
        try {
            switch (message) {
                case "nack":
                    // 第二个参数控制是否开启批量拒绝，第三个参数表示是否requeue
                    channel.basicNack(tag, true, false);
                    break;
                case "nack-requeue":
                    // 启用了requeue，如果只有一个消费者，容易造成死循环
                    channel.basicNack(tag, true, true);
                    break;
                case "reject":
                    channel.basicReject(tag, false);
                    break;
                case "reject-requeue":
                    // 启用了requeue，如果只有一个消费者，容易造成死循环
                    channel.basicReject(tag, true);
                    break;
                default:
                    // 手工ACK,yml中设置 acknowledge-mode: manual（默认为自动确认）
                    channel.basicAck(tag, true);
                    break;
            }
        } catch (IOException e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

}
