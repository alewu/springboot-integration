package com.ale.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@SuppressWarnings("unchecked")
@Component
public class ListRedisTemplate {
    @SuppressWarnings("rawtypes")
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @param key
     * @param t
     * @return t
     */
    public <T> Long lPush(String key, T t) {
        return redisTemplate.opsForList().leftPush(key, t);

    }

    /**
     * @param key
     * @return
     */
    public <T> T lPop(String key) {
        return (T) redisTemplate.opsForList().leftPop(key);
    }

    /**
     * @param key
     * @param t
     * @return
     */
    public <T> Long rpush(String key, T t) {
        return redisTemplate.opsForList().rightPush(key, t);
    }

    /**
     * @param key
     * @return
     */
    public <T> T rpush(String key) {
        return (T) redisTemplate.opsForList().leftPop(key);
    }

    /**
     * @param key
     * @return
     */
    public Long length(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * @param key
     * @param start
     * @param end
     * @return
     */
    public <T> List<T> range(String key, int start, int end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 移除key中值为value的i个,返回删除的个数
     *
     * @param key
     * @param i
     * @param value
     */
    public Long remove(String key, long i, String value) {
        return redisTemplate.opsForList().remove(key, i, value);
    }

    /**
     * 检索
     *
     * @param key
     * @param index
     * @return
     */
    public <T> T index(String key, long index) {
        return (T) redisTemplate.opsForList().index(key, index);
    }

    /**
     * 赋值
     *
     * @param key
     * @param index
     * @param t
     */
    public <T> void set(String key, long index, T t) {
        redisTemplate.opsForList().set(key, index, t);
    }

    /**
     * 裁剪,删除除了[start,end]以外的所有元素
     *
     * @param key
     * @param start
     * @param end
     */
    public void trim(String key, long start, int end) {
        redisTemplate.opsForList().trim(key, start, end);
    }
}

