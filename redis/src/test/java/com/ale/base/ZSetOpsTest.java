package com.ale.base;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * redis的有序集合在score相同的情况 下，redis使用字典排序
 *
 * @author alewu
 * @date 2020/6/8
 */
@SpringBootTest
@Slf4j
class ZSetOpsTest {

    @Autowired
    private RedisTemplate<String, String> strRedisTemplate;

    static BoundZSetOperations<String, String> zSetOperations = null;
    static String key = "";

    @BeforeEach
    void init() {
        key = "qr_code_average_allot:link_id:1235";
        zSetOperations = strRedisTemplate.boundZSetOps(key);
    }

    /**
     * 在zset中批量添加数据
     */
    @Test
    void testZaddsSet() {
        Set<ZSetOperations.TypedTuple<String>> set = new HashSet<>();
        DefaultTypedTuple<String> defaultTypedTuple1 = new DefaultTypedTuple<>("b", 1.0);
        DefaultTypedTuple<String> defaultTypedTuple2 = new DefaultTypedTuple<>("c", 0.0);
        DefaultTypedTuple<String> defaultTypedTuple3 = new DefaultTypedTuple<>("d", 1.0);
        set.add(defaultTypedTuple1);
        set.add(defaultTypedTuple2);
        set.add(defaultTypedTuple3);
        zSetOperations.add(set);
        System.out.println(zSetOperations.range(0, -1));
    }

    /**
     * 在zset中添加数据
     */
    @Test
    void testReZaddsSet() {
        Set<ZSetOperations.TypedTuple<String>> set = new HashSet<>();
        DefaultTypedTuple<String> defaultTypedTuple1 = new DefaultTypedTuple<>("b", 5.0);
        set.add(defaultTypedTuple1);
        zSetOperations.add(set);
        log.info("{}", zSetOperations.range(0, -1));
    }

    @Test
    void testReZaddsSet1() {
        strRedisTemplate.opsForZSet().add(key, "b", 12);
        System.out.println(zSetOperations.range(0, -1));
    }

    /**
     * 获取zset中指定score范围值内的元素
     */
    @Test
    void testZrangeByScoreSet() {
        System.out.println(zSetOperations.rangeByScore(0.0, 2.0));
    }

    /**
     * 根据score的区间值统计zset在改score区间中的元素个数
     */
    @Test
    void testZcountSet() {
        System.out.println(zSetOperations.count(1.0, 1.0));
    }

    @Test
    void testIncrementScore() {
        zSetOperations.incrementScore("b", -1.0);
    }

    @Test
    void testRangeWithScores() {
        Set<ZSetOperations.TypedTuple<String>> typedTuples = zSetOperations.rangeWithScores(0, -1);
        Iterator<ZSetOperations.TypedTuple<String>> iterator = typedTuples.iterator();
        while (iterator.hasNext()) {
            ZSetOperations.TypedTuple<String> typedTuple = iterator.next();
            System.out.println("value:" + typedTuple.getValue() + "score:" + typedTuple.getScore());
        }
    }

    @Test
    void testZSetWithScore() {
        long offset = 0;
        long count = 5;
        double max = 10.0;
        double min = 0.0;
        Set<ZSetOperations.TypedTuple<String>> typedTuples = Optional.ofNullable(strRedisTemplate.opsForZSet()
                                                                                                 .rangeByScoreWithScores(key, min, max,
                                                                                                                         offset, count))
                                                                     .orElse(Collections.emptySet());


        for (ZSetOperations.TypedTuple<String> stringTypedTuple : typedTuples) {
            System.out.println(stringTypedTuple.getValue());
            System.out.println(stringTypedTuple.getScore());
        }
        Assertions.assertEquals(count, typedTuples.size());
    }

    /**
     * 获取集合中数据
     */
    @Test
    void testGetFirstElement() {
        BoundZSetOperations<String, String> zSetOperations = strRedisTemplate.boundZSetOps(key);
        Set<ZSetOperations.TypedTuple<String>> typedTuples =
                Optional.ofNullable(zSetOperations.rangeWithScores(0, 0)).orElse(Collections.emptySet());
        String value = typedTuples.stream().findFirst().map(ZSetOperations.TypedTuple::getValue).orElse("");
        Double score = typedTuples.stream().findFirst().map(ZSetOperations.TypedTuple::getScore).orElse(0.0);
        System.out.println(value);
        System.out.println(score);
        zSetOperations.incrementScore(value, 1.0);
        zSetOperations.incrementScore(value, 1.0);
    }

    @Test
    void testGetRandomElement() {
        Set<String> range = Optional.ofNullable(zSetOperations.rangeByScore(1.0, 1.0)).orElse(Collections.emptySet());
        List<String> ids = Lists.newArrayList(range);
        int i = ThreadLocalRandom.current().nextInt(range.size());
        String s = ids.get(i);
        System.out.println(s);

    }

}
