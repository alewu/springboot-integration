package com.ale.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
  *  连接池配置
  * @author alewu
  * @date 2019/8/25
  */
@Data
@Component
@PropertySource(value = {"classpath:redis.properties"})
public class CommonsPool2Properties {
    @Value("${lettuce.pool.maxTotal}")
    private Integer maxTotal;

    @Value("${lettuce.pool.maxIdle}")
    private Integer maxIdle;

    @Value("${lettuce.pool.minIdle}")
    private Integer minIdle;
}
