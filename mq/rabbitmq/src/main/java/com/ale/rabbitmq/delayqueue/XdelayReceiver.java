package com.ale.rabbitmq.delayqueue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Configuration
@Slf4j
public class XdelayReceiver {

    @RabbitListener(queues = XdelayConfig.DELAY_QUEUE)
    public void cfgUserReceiveDealy(Booking booking, Message message) throws IOException {
        log.info("===============接收队列接收消息====================");
        log.info("接收时间:{},接受内容:{}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), booking.getBookingName());
    }
}