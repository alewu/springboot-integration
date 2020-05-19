package com.ale.rabbitmq.topic;


import com.ale.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author alewu
 * @date 2018/7/8 21:35
 */
@SpringBootTest
@AllArgsConstructor
public class TopicSend {
    private final RabbitMessagingTemplate rabbitMessagingTemplate;
    private final static String EXCHANGE_NAME = "test_exchange_topic";

    @Test
    public void testSendTopic(){
        rabbitMessagingTemplate.convertAndSend(EXCHANGE_NAME,"Hello World! topic");
    }

    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        // 消息内容
        String message = "Hello World! topic";
        channel.basicPublish(EXCHANGE_NAME, "item.update", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}
