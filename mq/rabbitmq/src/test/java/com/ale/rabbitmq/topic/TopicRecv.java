package com.ale.rabbitmq.topic;


import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.test.context.SpringBootTest;

import static com.ale.rabbitmq.constants.RabbitConstants.TOPIC_QUEUE_1_NAME;

/**
 * @author alewu
 * @date 2018/7/8 21:36
 */
@SpringBootTest
public class TopicRecv {

    @RabbitListener(queues = TOPIC_QUEUE_1_NAME)
    public void listen(String message) {
        System.out.println("Message read from myQueue : " + message);
    }

    @Test
    public void testRecv() {
    }

}
