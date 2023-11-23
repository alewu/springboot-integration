package com.ale.cahce.redis.config;

import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.util.StringUtils;

import java.time.Duration;

/**
 * redis 配置类
 * @author wywuj
 */
public class CustomRedisCacheManager extends RedisCacheManager {


    public CustomRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);
    }

    private static final RedisSerializationContext.SerializationPair<Object> DEFAULT_PAIR = RedisSerializationContext.SerializationPair
            .fromSerializer(new GenericJackson2JsonRedisSerializer());

    private static final CacheKeyPrefix DEFAULT_CACHE_KEY_PREFIX = cacheName -> cacheName+":";

    @Override
    protected RedisCache createRedisCache(String name, RedisCacheConfiguration cacheConfig) {
        String[] split = StringUtils.split(name, "#");
        if (split.length > 0) {
            final String ttl = split[1];
            final Duration duration = Duration.ofSeconds(Long.parseLong(ttl));
            cacheConfig = cacheConfig.entryTtl(duration);
            //修改缓存key和value值的序列化方式
            cacheConfig = cacheConfig.computePrefixWith(DEFAULT_CACHE_KEY_PREFIX)
                    .serializeValuesWith(DEFAULT_PAIR);
            final String cacheName = split[0];
            return super.createRedisCache(cacheName, cacheConfig);
        }
        throw new RuntimeException("字符串中必须包含#号，#号后为过期时间, -1为不过时");
    }


}
