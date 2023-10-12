package com.ale.cahce.redis.model;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author alewu
 * @date 2020/7/7
 */
@Data
@AllArgsConstructor
public class Customer {
    private Long id;
    private String email;
    private String name;
}