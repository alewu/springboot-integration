package com.ale.rabbitmq.topic;


import com.ale.rabbitmq.constants.RabbitConstants;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.ale.rabbitmq.constants.RabbitConstants.BINDING_PATTERN_IMPORTANT;

/**
 * @author alewu
 * @date 2018/7/8 21:35
 */
@SpringBootTest
public class TopicSend {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendTopic() {
        rabbitTemplate.convertSendAndReceive(RabbitConstants.TOPIC_EXCHANGE_NAME,
                                                        BINDING_PATTERN_IMPORTANT, "Hello World! " +
                "topic");
    }

}
