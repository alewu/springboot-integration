package com.ale;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author ale
 */
@SpringBootApplication
public class MvcSpringbootApplication {
    public static void main(String[] args) {
        SpringApplication.run(MvcSpringbootApplication.class, args);
    }

    @Bean
    CommandLineRunner initProxyClientRunner(final RestTemplate restTemplate) {
        return runArgs -> {

        };
    }
}
