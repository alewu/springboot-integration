package com.ale.cahce.redis.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.TreeMap;

@SpringBootTest
public class CachingWithRedisIntegrationTest {
    @Resource
    private ProductService productService;

    @Test
    public void thenCacheHits() {
        TreeMap<String, String> product = productService.getProduct("123");
        System.out.println(product);
    }

}
