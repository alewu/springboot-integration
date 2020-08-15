package com.ale.rabbitmq.delayqueue.dlx.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Header;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.ale.rabbitmq.delayqueue.dlx.config.DLQConfig.DEAD_LETTER_QUEUE;

@Slf4j
public class DLQAmqpContainer {
    private final RabbitTemplate rabbitTemplate;

    public DLQAmqpContainer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = DEAD_LETTER_QUEUE)
    public void processFailedMessages(Message message, @Header(required = false, name = "x-death") List<Map<String,
            Object>> xDeath) {
        Date date = (Date) xDeath.stream().findFirst().map(a -> a.get("time")).orElse(new Date());
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        log.info("Received time: {}", LocalDateTime.now());
        log.info("Received dead letter message: {}", message.toString());
    }


}
