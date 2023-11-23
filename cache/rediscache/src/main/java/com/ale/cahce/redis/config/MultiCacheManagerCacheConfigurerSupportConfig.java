package com.ale.cahce.redis.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
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

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author alewu
 * @date 2022/05/21
 */
//@Configuration
//@EnableCaching
public class MultiCacheManagerCacheConfigurerSupportConfig extends CachingConfigurerSupport {

    public String[] cacheNames = {
            "products"
    };

    /**
     * We are using CachingConfigurerSupport to define out main caching
     * provider. In our case it's Caffeine cache. This will be the default cache provider
     * for our application. If we don't provide explicit cache manager, Spring Boot
     * will pick this as default cache provider.
     *
     * @return
     */
    @Override
    @Bean // good to have but not strictly necessary
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCacheNames(Arrays.asList(
                "customers",
                "products"
        ));
        cacheManager.setCaffeine(caffeineCacheBuilder());
        return cacheManager;
    }

    Caffeine<Object, Object> caffeineCacheBuilder() {
        return Caffeine.newBuilder()
                       .initialCapacity(100)
                       .maximumSize(500)
                       .expireAfterAccess(10, TimeUnit.MINUTES)
                       .weakKeys()
                       .recordStats();
    }

    /**
     * Second cache provider which can work as fallback or will be used when invoked explicitly in the
     * code base.
     */
    @Bean
    public CacheManager alternateCacheManager() {
        return new ConcurrentMapCacheManager(cacheNames);
    }
}
