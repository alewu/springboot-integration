package com.ale.rabbitmq.exchangetype.listener;

import com.ale.rabbitmq.exchangetype.service.AsyncTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.ale.rabbitmq.constants.RabbitConstants.FANOUT_QUEUE_1_NAME;

/**
 * @author alewu
 * @date 2020/6/21
 */
@Component
@Slf4j
public class FanOutQueueListener {
    @Autowired
    private AsyncTaskService asyncTaskService;

    @RabbitListener(queues = {FANOUT_QUEUE_1_NAME})
    public void receiveMessageFromFanout1(String message) {
        //        System.out.println("Received fanout 1 message: " + message);
        asyncTaskService.sendMsgAsync(message);
        //        asyncTaskService.sendMsg(message);
    }

}
