package com.ale;

import com.ale.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author alewu
 * @date 2020/7/7
 */
@SpringBootApplication
@Slf4j
public class SpringCacheApplication {

    @Autowired
    private CustomerService customerService;

    public static void main(String[] args) {
        SpringApplication.run(SpringCacheApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner() {
        return args -> {
            log.info("Starting the Caffine cache testing process");
            customerService.getCustomer(1L); //No hit , since this is the first request.
            customerService.getCustomer(2L); //No hit , since this is the first request.
            customerService.getCustomer(1L); //hit , since it is already in the cache.
            customerService.getCustomer(1L); //hit , since it is already in the cache.
            customerService.getCustomer(1L); //hit , since it is already in the cache.
            customerService.getCustomer(1L); //hit , since it is already in the cache.};
        };


    }
}
