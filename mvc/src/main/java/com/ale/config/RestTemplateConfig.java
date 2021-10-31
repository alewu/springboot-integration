package com.ale.config;

import com.ale.interceptor.CookieClientHttpRequestInterceptor;
import com.ale.interceptor.LoggingClientHttpRequestInterceptor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfig {

    @Bean("restTemplate")
    public RestTemplate restTemplate(RestTemplateBuilder builder, CookieClientHttpRequestInterceptor cookieClientHttpRequestInterceptor ) {
        return builder
                .requestFactory(()->new BufferingClientHttpRequestFactory(new HttpComponentsClientHttpRequestFactory()))
                .setConnectTimeout(Duration.ofSeconds(30))
                .setReadTimeout(Duration.ofSeconds(30))
                .interceptors(cookieClientHttpRequestInterceptor, new LoggingClientHttpRequestInterceptor())
                .build();
    }

}
