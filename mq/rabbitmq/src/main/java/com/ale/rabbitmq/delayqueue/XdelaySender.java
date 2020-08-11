package com.ale.rabbitmq.delayqueue;

import com.ale.rabbitmq.delayqueue.config.XdelayConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@Slf4j
public class XdelaySender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(Booking booking, int delayTime) {
        //这里的消息可以是任意对象，无需额外配置，直接传即可
        log.info("===============延时队列生产消息====================");
        String id = UUID.randomUUID().toString();
        log.info("消息id：{}, 发送时间:{},发送内容:{}", id, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                 booking.getBookingName());
        CorrelationData correlationData = new CorrelationData(id);
        rabbitTemplate.convertAndSend(
                XdelayConfig.DELAY_EXCHANGE,
                XdelayConfig.DELAY_QUEUE_ONE,
                booking,
                message -> {
                    //注意这里时间可以使long，而且是设置header
                    MessageProperties messageProperties = message.getMessageProperties();
                    messageProperties.setMessageId(id);
                    messageProperties.setAppId("book");
                    messageProperties.setDelay(delayTime * 1000);
                    // messageProperties.setHeader("x-delay", delayTime * 60000);
                    return message;
                }, correlationData
        );

    }

    public void sendOrder(Order order, int delayTime) {
        //这里的消息可以是任意对象，无需额外配置，直接传即可
        log.info("===============延时队列生产消息====================");
        String id = UUID.randomUUID().toString();
        log.info("发送时间:{},发送内容: 第{}个订单", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                 order.getId());
        CorrelationData correlationData = new CorrelationData(id);
        rabbitTemplate.convertAndSend(
                XdelayConfig.DELAY_EXCHANGE,
                XdelayConfig.DELAY_QUEUE_TWO,
                order,
                message -> {
                    //注意这里时间可以使long，而且是设置header
                    MessageProperties messageProperties = message.getMessageProperties();
                    messageProperties.setCorrelationId("correlationId-2");
                    messageProperties.setAppId("order");
                    // 单位: ms
                    messageProperties.setDelay(delayTime * 1000);
                    return message;
                }, correlationData
        );
    }
}