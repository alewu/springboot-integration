package com.ale.rest.service.impl;

import com.ale.domain.Person;
import com.ale.rest.service.HelloService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 服务实现类
 *
 * @author alewu
 * @date 2019-08-02
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String a) {
        return String.format("Hello %s, Welcome to CXF RS Spring Boot World!!!", a);
    }

    @Override
    public Person getPerson() {
        Person person = new Person();
        person.setName("jack");
        person.setProfession("java developer");
        person.setBirthday(new Date());
        System.out.println("ok");
        return person;
    }
}
