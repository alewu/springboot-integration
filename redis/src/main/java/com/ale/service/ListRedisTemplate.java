package com.ale.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The type List redis template.
 */
@SuppressWarnings("unchecked")
@Component
public class ListRedisTemplate {
    @SuppressWarnings("rawtypes")
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * L push long.
     *
     * @param <T> the type parameter
     * @param key the key
     * @param t   the t
     * @return t long
     */
    public <T> Long lPush(String key, T t) {
        return redisTemplate.opsForList().leftPush(key, t);

    }

    /**
     * L pop t.
     *
     * @param <T> the type parameter
     * @param key the key
     * @return t
     */
    public <T> T lPop(String key) {
        return (T) redisTemplate.opsForList().leftPop(key);
    }

    /**
     * Rpush long.
     *
     * @param <T> the type parameter
     * @param key the key
     * @param t   the t
     * @return long
     */
    public <T> Long rpush(String key, T t) {
        return redisTemplate.opsForList().rightPush(key, t);
    }

    /**
     * Rpush t.
     *
     * @param <T> the type parameter
     * @param key the key
     * @return t
     */
    public <T> T rpush(String key) {
        return (T) redisTemplate.opsForList().leftPop(key);
    }

    /**
     * Length long.
     *
     * @param key the key
     * @return long
     */
    public Long length(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * Range list.
     *
     * @param <T>   the type parameter
     * @param key   the key
     * @param start the start
     * @param end   the end
     * @return list
     */
    public <T> List<T> range(String key, int start, int end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 移除key中值为value的i个,返回删除的个数
     *
     * @param key   the key
     * @param i     the
     * @param value the value
     * @return the long
     */
    public Long remove(String key, long i, String value) {
        return redisTemplate.opsForList().remove(key, i, value);
    }

    /**
     * 检索
     *
     * @param <T>   the type parameter
     * @param key   the key
     * @param index the index
     * @return t
     */
    public <T> T index(String key, long index) {
        return (T) redisTemplate.opsForList().index(key, index);
    }

    /**
     * 赋值
     *
     * @param <T>   the type parameter
     * @param key   the key
     * @param index the index
     * @param t     the t
     */
    public <T> void set(String key, long index, T t) {
        redisTemplate.opsForList().set(key, index, t);
    }

    /**
     * 裁剪,删除除了[start,end]以外的所有元素
     *
     * @param key   the key
     * @param start the start
     * @param end   the end
     */
    public void trim(String key, long start, int end) {
        redisTemplate.opsForList().trim(key, start, end);
    }
}

