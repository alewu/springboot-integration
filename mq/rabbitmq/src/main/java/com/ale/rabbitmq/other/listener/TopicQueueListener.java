package com.ale.rabbitmq.other.listener;

import com.ale.rabbitmq.service.AsyncTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.ale.rabbitmq.constants.RabbitConstants.*;

/**
 * @author alewu
 * @date 2020/6/21
 */
@Component
@Slf4j
public class TopicQueueListener {
    @Autowired
    private AsyncTaskService asyncTaskService;

    @RabbitListener(queues = {FANOUT_QUEUE_2_NAME})
    public void receiveMessageFromFanout2(String message) {
        System.out.println("Received fanout 2 message: " + message);
        asyncTaskService.sendMsg(message);
    }

    @RabbitListener(queues = {TOPIC_QUEUE_1_NAME})
    public void receiveMessageFromTopic1(String message) {
        System.out.println("Received topic 1 (" + BINDING_PATTERN_IMPORTANT + ") message: " + message);
    }

    @RabbitListener(queues = {TOPIC_QUEUE_2_NAME})
    public void receiveMessageFromTopic2(String message) {
        System.out.println("Received topic 2 (" + BINDING_PATTERN_ERROR + ") message: " + message);
    }

}
