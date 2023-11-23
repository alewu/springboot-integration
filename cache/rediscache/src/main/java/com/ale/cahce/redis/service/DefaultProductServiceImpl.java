package com.ale.cahce.redis.service;

import com.ale.cahce.redis.aspect.RedisCache;
import com.ale.cahce.redis.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.TreeMap;

@Service("productService")
@Slf4j
public class DefaultProductServiceImpl implements ProductService {

    @Override
    public Product getProductByCode(String code) {
        log.info("Get product by code {} ", code);
        Product product = new Product();
        product.setCode(code);
        product.setBrand("Sony");
        product.setDescription("Sony new camera");
        product.setName("Sony Alpha A7S");
        return product;
    }

    @Override
    @Cacheable(cacheNames = "product#123")
    public TreeMap<String, String> getProduct(String code) {
        log.info("Get product by code {} ", code);
        Product product = new Product();
        product.setCode(code);
        product.setBrand("Sony");
        product.setDescription("Sony new camera");
        product.setName("Sony Alpha A7S");
        TreeMap<String, String> stringStringHashMap = new TreeMap<>();
        stringStringHashMap.put("aa", "aa");
        return stringStringHashMap;
    }
}