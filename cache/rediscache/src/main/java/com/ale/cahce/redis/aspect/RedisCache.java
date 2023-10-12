package com.ale.cahce.redis.aspect;

/**
 * @author wywuj
 */
public @interface RedisCache {
    String key() default "";
}
