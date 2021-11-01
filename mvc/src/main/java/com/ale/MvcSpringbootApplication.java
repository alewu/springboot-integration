package com.ale;

import com.ale.common.ResponseResult;
import com.ale.rest.RestDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

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
            TimeUnit.SECONDS.sleep(1);
            HttpEntity<String> requestEntity = new HttpEntity<>("params");
            requestEntity.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            ResponseEntity<ResponseResult<RestDTO>> responseEntity = restTemplate.exchange("http://localhost:11111/rest/test",
                                                                                     HttpMethod.POST, requestEntity,
                                                                                     new ParameterizedTypeReference<ResponseResult<RestDTO>>() {
                                                                                     });
            System.out.println(responseEntity);

        };
    }
}
