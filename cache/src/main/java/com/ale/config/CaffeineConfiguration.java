package com.ale.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * @author alewu
 * @date 2020/7/7
 */
@Configuration
@EnableCaching
public class CaffeineConfiguration {

    //
    //    @Bean
    //    public Caffeine<Object, Object> caffeineConfig() {
    //        return Caffeine.newBuilder().expireAfterWrite(60, TimeUnit.MINUTES);
    //    }
    //
    //    @Bean
    //    public CacheManager cacheManager(Caffeine<Object, Object> caffeine) {
    //        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
    //        caffeineCacheManager.setCaffeine(caffeine);
    //        return caffeineCacheManager;
    //    }
}
