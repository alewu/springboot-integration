package com.ale.rabbitmq.delayqueue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Configuration
@Slf4j
public class XdelayReceiver {

    //    @RabbitListener(queues = XdelayConfig.DELAY_QUEUE)
    public void cfgUserReceiveDelay(Message<Booking> message) throws IOException {
        Booking booking = message.getPayload();
        String appId = String.valueOf(message.getHeaders().get("amqp_appId"));
        String correlationId = String.valueOf(message.getHeaders().get("amqp_correlationId"));
        log.info("===============接收队列接收消息====================");
        log.info("correlationId:{}", correlationId);
        log.info("发送人：{},接收时间:{},接受内容:{}", appId, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), booking.getBookingName());
    }

    @RabbitListener(queues = XdelayConfig.DELAY_QUEUE)
    public void userReceiveDelay(@Payload Booking booking, @Header("amqp_correlationId") String correlationId,
                                 @Header("amqp_appId") String appId) throws IOException {
        log.info("===============接收队列接收消息====================");
        log.info("@Header correlationId:{}", correlationId);
        log.info("发送人：{},接收时间:{},接受内容:{}", appId, LocalDateTime.now().format(DateTimeFormatter.ofPattern(
                "yyyy-MM-dd HH:mm:ss")), booking.getBookingName());
    }
}