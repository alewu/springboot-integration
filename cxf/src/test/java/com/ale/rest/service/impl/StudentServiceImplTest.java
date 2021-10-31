package com.ale.rest.service.impl;

import com.ale.CXFSpringBootApplication;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(classes = CXFSpringBootApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentServiceImplTest {

    @LocalServerPort
    private int port;

    @Test
    void sayHello() {
        WebClient wc = WebClient.create("http://localhost:" + port + "/services/testService");

        wc.accept("text/plain");

        // personServiceImpl
        wc.path("sayHello").path("ApacheCxfUser");

        String greeting = wc.get(String.class);
//        Assert.assertEquals("Hello ApacheCxfUser, Welcome to CXF RS Spring Boot World!!!", greeting);

        // Reverse to the starting URI
        wc.back(true);
    }
}