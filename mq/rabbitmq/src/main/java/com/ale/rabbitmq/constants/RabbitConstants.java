package com.ale.rabbitmq.constants;

/**
 * @author alewu
 */
public final class RabbitConstants {
    public static final boolean NON_DURABLE = false;

    public static final String FANOUT_QUEUE_1_NAME = "com.baeldung.spring-amqp-simple.fanout.queue1";
    public static final String FANOUT_QUEUE_2_NAME = "com.baeldung.spring-amqp-simple.fanout.queue2";
    public static final String FANOUT_EXCHANGE_NAME = "com.baeldung.spring-amqp-simple.fanout.exchange";

    public static final String TOPIC_QUEUE_1_NAME = "com.baeldung.spring-amqp-simple.topic.queue1";
    public static final String TOPIC_QUEUE_2_NAME = "com.baeldung.spring-amqp-simple.topic.queue2";
    public static final String TOPIC_EXCHANGE_NAME = "com.baeldung.spring-amqp-simple.topic.exchange";

    public static final String BINDING_PATTERN_IMPORTANT = "*.important.*";
    public static final String BINDING_PATTERN_ERROR = "#.error";

    public static String ROUTING_KEY_USER_IMPORTANT_WARN = "user.important.warn";
    public static String ROUTING_KEY_USER_IMPORTANT_ERROR = "user.important.error";

    public static final String MY_QUEUE = "myQueue";
}
