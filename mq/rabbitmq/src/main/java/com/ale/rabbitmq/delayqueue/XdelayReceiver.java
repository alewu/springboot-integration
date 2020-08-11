package com.ale.rabbitmq.delayqueue;

import com.ale.rabbitmq.delayqueue.config.XdelayConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Configuration
@Slf4j
public class XdelayReceiver {

    @RabbitListener(queues = XdelayConfig.DELAY_QUEUE_ONE)
    public void cfgUserReceiveDelay(Message<Booking> message) {
        Booking booking = message.getPayload();
        String appId = String.valueOf(message.getHeaders().get("amqp_appId"));
        String msgId = String.valueOf(message.getHeaders().get("amqp_messageId"));
        log.info("===============接收队列接收书本消息====================");
        log.info("msgId:{}", msgId);
        log.info("发送人：{},接收时间:{},接受内容:{}", appId, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), booking.getBookingName());
    }

    @RabbitListener(queues = XdelayConfig.DELAY_QUEUE_TWO)
    public void userReceiveDelay(@Payload Order order, @Header(AmqpHeaders.CORRELATION_ID) String correlationId,
                                 @Header(AmqpHeaders.APP_ID) String appId) {
        log.info("===============接收队列接收订单消息====================");
        log.info("@Header correlationId:{}", correlationId);
        log.info("发送人：{},接收时间:{},接受内容: 第{}个订单", appId, LocalDateTime.now().format(DateTimeFormatter.ofPattern(
                "yyyy-MM-dd HH:mm:ss")), order.getId());
    }
}