package com.ale.redis.pubsub;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;

@Configuration
public class RedisConfig {

    @Bean("channelTopic")
    public Topic channelTopic() {
        return new ChannelTopic("news");
    }

    @Bean("patternTopic")
    public Topic patternTopic() {
        return new PatternTopic("log.*");
    }

    @Bean
    public SubscribeMessageListener listener() {
        return new SubscribeMessageListener();
    }

    @Bean
    RedisMessageListenerContainer container(LettuceConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listener(), patternTopic());
        container.addMessageListener(listener(), channelTopic());
        return container;
    }

    //    @Bean
    //    MessageListenerAdapter listenerAdapter(MessageReceiver receiver) {
    //        //给messageListenerAdapter 传入一个消息接受的处理器，利用反射的方法调用“receiveMessage”
    //        //不填defaultListenerMethod默认调用handleMessage
    //        return new MyMessageListenerAdapter(receiver, "receiverMessage");
    //    }
}
