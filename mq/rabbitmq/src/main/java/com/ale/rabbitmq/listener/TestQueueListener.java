package com.ale.rabbitmq.listener;

import com.ale.rabbitmq.model.User;
import com.ale.rabbitmq.service.AsyncTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.ale.rabbitmq.constants.RabbitConstants.FANOUT_QUEUE_1_NAME;
import static com.ale.rabbitmq.constants.RabbitConstants.MY_QUEUE;

/**
 * @author alewu
 * @date 2020/6/21
 */
@Component
@Slf4j
public class TestQueueListener {
    @Autowired
    private AsyncTaskService asyncTaskService;

    @RabbitListener(queues = {FANOUT_QUEUE_1_NAME})
    public void receiveMessageFromFanout1(String message) {
        //        System.out.println("Received fanout 1 message: " + message);
        asyncTaskService.sendMsgAsync(message);
        //        asyncTaskService.sendMsg(message);
    }

    @RabbitListener(queues = {MY_QUEUE})
    public void receiveMessageFromDirect(User user) {
//        log.info("Received direct message: {}", user);
                asyncTaskService.sendMsgAsync(user.toString());
        //        asyncTaskService.sendMsg(message);
    }

/*
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
*/

}
