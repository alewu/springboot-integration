package com.ale;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ale
 */
@SpringBootApplication
public class MvcSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MvcSpringbootApplication.class, args);
    }

//    @Bean
CommandLineRunner initProxyClientRunner() {
    return runArgs -> {
    };
}
}
