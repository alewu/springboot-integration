package com.ale.rabbitmq.errorhandling.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static com.ale.rabbitmq.errorhandling.config.DLXParkingLotAmqpConfig.EXCHANGE_PARKING_LOT;
import static com.ale.rabbitmq.errorhandling.config.DLXParkingLotAmqpConfig.QUEUE_PARKING_LOT;
import static com.ale.rabbitmq.errorhandling.config.SimpleDLQAmqpConfig.EXCHANGE_MESSAGES;
import static com.ale.rabbitmq.errorhandling.config.SimpleDLQAmqpConfig.QUEUE_MESSAGES_DLQ;
import static com.ale.rabbitmq.errorhandling.consumer.MessagesConsumer.HEADER_X_RETRIES_COUNT;

@Slf4j
public class ParkingLotDLQAmqpContainer {
    public static final int MAX_RETRIES_COUNT = 2;
    private final RabbitTemplate rabbitTemplate;

    public ParkingLotDLQAmqpContainer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = QUEUE_MESSAGES_DLQ)
    public void processFailedMessagesRetryWithParkingLot(Message failedMessage) {
        Integer retriesCnt = (Integer) failedMessage.getMessageProperties().getHeaders().get(HEADER_X_RETRIES_COUNT);
        if (retriesCnt == null) {
            retriesCnt = 1;
        }
        if (retriesCnt > MAX_RETRIES_COUNT) {
            log.info("Sending message to the parking lot queue");
            rabbitTemplate.send(EXCHANGE_PARKING_LOT, failedMessage.getMessageProperties().getReceivedRoutingKey(),
                                failedMessage);
            return;
        }
        log.info("Retrying message for the {} time", retriesCnt);
        failedMessage.getMessageProperties().getHeaders().put(HEADER_X_RETRIES_COUNT, ++retriesCnt);
        rabbitTemplate.send(EXCHANGE_MESSAGES, failedMessage.getMessageProperties().getReceivedRoutingKey(),
                            failedMessage);
    }

    @RabbitListener(queues = QUEUE_PARKING_LOT)
    public void processParkingLotQueue(Message failedMessage) {
        log.info("Received message in parking lot queue {}", failedMessage.toString());
    }
}
