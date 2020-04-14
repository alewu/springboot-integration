package com.ale.rabbitmq.util;


import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author alewu
 * @date 2018/7/5 12:04
 */
public class ConnectionUtil {

    public static Connection getConnection() throws Exception {
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost("localhost");
        //端口，HTTP管理端口默认15672，访问端口默认5672
        factory.setPort(5672);
        //设置账号信息，用户名、密码、virtual host
        //注意这个值，vh配置如果加了斜杠，这里也必须加
        factory.setVirtualHost("/rabbitmq");
        factory.setUsername("admin");
        factory.setPassword("admin");
        // 通过工程获取连接
        return factory.newConnection();
    }
}
