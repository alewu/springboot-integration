package com.ale.rest.service.impl;

import com.ale.MySpringBootApplication;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(classes = MySpringBootApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloServiceImplTest {

    @LocalServerPort
    private int port;

    @Test
    void sayHello() {
        WebClient wc = WebClient.create("http://localhost:" + port + "/services/helloService");

        wc.accept("text/plain");

        // HelloServiceImpl
        wc.path("sayHello").path("ApacheCxfUser");

        String greeting = wc.get(String.class);
        Assert.assertEquals("Hello ApacheCxfUser, Welcome to CXF RS Spring Boot World!!!", greeting);

        // Reverse to the starting URI
        wc.back(true);
    }
}