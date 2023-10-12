package com.ale.cahce.redis.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @author alewu
 * @date 2020/7/7
 */
@Configuration
@EnableCaching
public class MultiCacheManagerPrimaryConfig {

    @Bean
    public Caffeine<Object, Object> caffeineConfig() {
        return Caffeine.newBuilder().expireAfterWrite(60, TimeUnit.MINUTES);
    }

    @Bean("caffeineCache")
    @Primary
    public CacheManager cacheManager(Caffeine<Object, Object> caffeine) {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }


    private final Duration timeToLive = Duration.ofSeconds(10);

    /**
     * Spring2.2.6 自己配置redis的CacheManager
     * entryTtl 实体生存时间 通过设置键值的序列化方式来进行缓存实体的序列化
     *
     * @param connectionFactory {@link RedisConnectionFactory}redis链接工厂
     * @return {@link RedisCacheManager}
     */
    @Bean("redisCache")
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                                                                // 设置失效时间
                                                                .entryTtl(this.timeToLive)
                                                                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer()))
                                                                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer()))
                                                                .disableCachingNullValues(); // 不允许缓存value
        // 为空值的情况，如果Value为null，会导致报错
        RedisCacheManager redisCacheManager;
        redisCacheManager = RedisCacheManager.builder(connectionFactory)
                                             .cacheDefaults(config)
                                             .transactionAware()
                                             .build();
        return redisCacheManager;
    }

//    @Bean
//    RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
//        return (builder) -> {
//            Map<String, RedisCacheConfiguration> configurationMap = new HashMap<>();
//            configurationMap.put("redisCache1", getRedisCacheConfigurationWithDuration(Duration.ofSeconds(10)));
//            configurationMap.put("redisCache2", getRedisCacheConfigurationWithDuration(Duration.ofSeconds(20)));
//            builder.withInitialCacheConfigurations(configurationMap);
//        };
//    }
//
//    private RedisCacheConfiguration getRedisCacheConfigurationWithDuration(Duration duration) {
//        return RedisCacheConfiguration.defaultCacheConfig()
//                                      // 设置失效时间
//                                      .entryTtl(duration)
//                                      .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer()))
//                                      .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer()))
//                                      .disableCachingNullValues();
//    }

    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(keySerializer());
        redisTemplate.setHashKeySerializer(keySerializer());
        redisTemplate.setValueSerializer(valueSerializer());
        redisTemplate.setHashValueSerializer(valueSerializer());
        return redisTemplate;
    }

    @Bean(name = "stringRedisTemplate")
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate redisTemplate = new StringRedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(keySerializer());
        redisTemplate.setHashKeySerializer(keySerializer());
        redisTemplate.setValueSerializer(valueSerializer());
        redisTemplate.setHashValueSerializer(valueSerializer());
        return redisTemplate;
    }

    private RedisSerializer<String> keySerializer() {
        return new StringRedisSerializer();
    }

    private RedisSerializer<Object> valueSerializer() {
        return new GenericJackson2JsonRedisSerializer();

    }
}
