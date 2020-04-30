package com.ale.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author alewu
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.ale.mapper")
public class MybatisConfig {

}
