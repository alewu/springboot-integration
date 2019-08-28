package com.ale.rest.service.cache;

import com.ale.domain.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.ws.soap.Addressing;

@SpringBootTest
class RedisDemoServiceImplTest {
    @Autowired
    RedisDemoService redisDemoService = new RedisDemoServiceImpl();

    @Test
    void testTestCache() {
        Person result = redisDemoService.testCache("name");
        Assertions.assertEquals(new Person(), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme