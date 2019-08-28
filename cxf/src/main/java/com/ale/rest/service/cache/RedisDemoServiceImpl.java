package com.ale.rest.service.cache;

import com.ale.domain.Person;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * redis实现类
 *
 * @author alewu
 * @date 2019/8/25
 */
@Service
public class RedisDemoServiceImpl implements RedisDemoService {

    @Override
    @Cacheable(value = "redisCache", key = "#name")
    public Person testCache(String name) {
        Person person = new Person();
        person.setName("jack");
        person.setProfession("java developer");
        person.setBirthday(new Date());
        return person;
    }
}
