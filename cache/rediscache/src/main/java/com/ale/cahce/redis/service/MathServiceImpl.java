package com.ale.cahce.redis.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author : wywuj
 * @since : 2023/11/23 8:11
 **/
@Service
public class MathServiceImpl implements MathService {
    private final AtomicBoolean cacheMiss = new AtomicBoolean(false);

    @Override
    public boolean wasCacheMiss() {
        return cacheMiss.getAndSet(false);
    }

    @Override
    @Cacheable(cacheNames = "Factorials")
    public long factorial(long number) {
        cacheMiss.set(true);

        Assert.isTrue(number >= 0L, String.format("Number [%d] must be greater than equal to 0", number));

        if (number <= 2L) {
            return (number < 2L ? 1L : 2L);
        }

        long result = number;

        while (--number > 1) {
            result *= number;
        }

        return result;
    }
}
