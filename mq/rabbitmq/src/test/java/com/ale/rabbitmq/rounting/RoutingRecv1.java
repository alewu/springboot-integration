package com.ale.rabbitmq.rounting;

import com.alewu.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author alewu
 * @date 2018/7/8 21:16
 */
public class RoutingRecv1 {

    private final static String QUEUE_NAME = "test_queue_direct_work";

    private final static String EXCHANGE_NAME = "test_exchange_direct";

    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 绑定队列到交换机
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "key2");

        // 同一时刻服务器只会发一条消息给消费者
        channel.basicQos(1);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);
                // 校验应答机制
//                int i = 10/0;
//                System.out.println(i);
                // 人工进行确认
                channel.basicAck(envelope.getDeliveryTag(), false);
                System.out.println(" [x] Received '" + message + "'");
            }
        };
        // 监听队列，手动返回完成
        channel.basicConsume(QUEUE_NAME, false, consumer);
    }
}
