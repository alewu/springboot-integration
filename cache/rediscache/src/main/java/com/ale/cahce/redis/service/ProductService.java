package com.ale.cahce.redis.service;

import com.ale.cahce.redis.model.Product;

import java.util.Map;
import java.util.TreeMap;

public interface ProductService {
    Product getProductByCode(String code);

    TreeMap<String, String> getProduct(String code);
}
