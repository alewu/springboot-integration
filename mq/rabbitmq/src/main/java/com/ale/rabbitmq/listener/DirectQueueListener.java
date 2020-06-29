package com.ale.rabbitmq.listener;

import com.ale.rabbitmq.model.User;
import com.ale.rabbitmq.service.AsyncTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.ale.rabbitmq.constants.RabbitConstants.MY_QUEUE;

/**
 * @author alewu
 * @date 2020/6/21
 */
@Component
@Slf4j
public class DirectQueueListener {
    @Autowired
    private AsyncTaskService asyncTaskService;


    @RabbitListener(queues = {MY_QUEUE}, containerFactory = "rabbitListenerContainerFactory")
    public void receiveMessageFromDirect(User user) {
        asyncTaskService.sendMsgAsync(user.toString());
    }

}
