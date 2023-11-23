package com.ale.cahce.redis.service;

/**
 * The interface Math service.
 *
 * @author : wywuj
 * @since : 2023/11/23 8:04
 */
public interface MathService {

    /**
     * Was cache miss boolean.
     *
     * @return the boolean
     */
    boolean wasCacheMiss();

    /**
     * Factorial long.
     *
     * @param number the number
     * @return the long
     */
    long factorial(long number);
}
