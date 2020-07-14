package com.ale.rabbitmq.delayqueue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class XdelaySender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(Booking booking, int delayTime) {
        //这里的消息可以是任意对象，无需额外配置，直接传即可
        log.info("===============延时队列生产消息====================");
        log.info("发送时间:{},发送内容:{}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), booking.getBookingName());
        this.rabbitTemplate.convertAndSend(
                XdelayConfig.DELAY_EXCHANGE,
                XdelayConfig.DELAY_KEY,
                booking,
                message -> {
                    //注意这里时间可以使long，而且是设置header
                    MessageProperties messageProperties = message.getMessageProperties();
                    messageProperties.setCorrelationId("correlationId-1");
                    messageProperties.setAppId("123");
                    messageProperties.setDelay(delayTime * 60000);//设置延迟多少分钟
                    //                    messageProperties.setHeader("x-delay", delayTime * 60000);
                    return message;
                }
        );
    }
}