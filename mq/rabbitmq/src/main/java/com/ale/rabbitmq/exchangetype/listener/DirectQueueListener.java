package com.ale.rabbitmq.exchangetype.listener;

import com.ale.rabbitmq.exchangetype.model.User;
import com.ale.rabbitmq.exchangetype.service.AsyncTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.ale.rabbitmq.constants.RabbitConstants.HIGH_QUEUE;
import static com.ale.rabbitmq.constants.RabbitConstants.MIDDLE_QUEUE;
import static com.ale.rabbitmq.constants.RabbitConstants.SLOW_QUEUE;

/**
 * @author alewu
 * @date 2020/6/21
 */
@Component
@Slf4j
public class DirectQueueListener {
    @Autowired
    private AsyncTaskService asyncTaskService;

    //    @RabbitListener(queues = {MY_QUEUE})
    //    public void receiveMessageFromDirect(@Payload User user) {
    //        //        asyncTaskService.sendMsgAsync(user.toString());
    //    }

    @RabbitListener(queues = {SLOW_QUEUE})
    public void receiveMessageFromSlowDirect(@Payload User user) {
        asyncTaskService.sendMsgAsync3(user.toString());
    }

    @RabbitListener(queues = {MIDDLE_QUEUE})
    public void receiveMessageFromMiddleDirect(@Payload User user) {
        asyncTaskService.sendMsgAsync1(user.toString());
    }

    @RabbitListener(queues = {HIGH_QUEUE})
    public void receiveMessageFromHighDirect(@Payload User user) {
        asyncTaskService.sendMsgAsync2(user.toString());
    }

}
