package com.ale;

import com.ale.rest.client.HelloService;
import org.apache.cxf.jaxrs.client.spring.EnableJaxRsProxyClient;
import org.json.JSONObject;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 * @author alewu
 */
@SpringBootApplication
@ImportResource(locations = {"classpath*: spring-cxf-rest-services.xml"})
@EnableJaxRsProxyClient
public class CXFSpringBootApplication {


    public static void main(String[] args) {
        SpringApplication.run(CXFSpringBootApplication.class, args);
    }


    @Bean
    CommandLineRunner initProxyClientRunner(final HelloService client) {
        return runArgs -> {
           client.sayHello("cxf");
        };
    }


}
