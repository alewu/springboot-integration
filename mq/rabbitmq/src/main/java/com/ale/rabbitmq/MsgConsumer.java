package com.ale.rabbitmq;

import com.ale.rabbitmq.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.ale.rabbitmq.constants.RabbitConstants.TOPIC_QUEUE_1_NAME;
/**
  *
  * @author alewu
  * @date 2020/5/20
  */
@Component
@Slf4j
public class MsgConsumer {

    @RabbitListener(queues = TOPIC_QUEUE_1_NAME)
    public void process(User message) {
        log.info("receive: " + message);
    }

}
