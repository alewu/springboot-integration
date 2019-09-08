package com.ale.rest.service.cache;

import com.ale.domain.Student;
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
    public Student testCache(String name) {
        Student student = new Student();
        student.setName("jack");
        student.setProfession("java developer");
        student.setBirthday(new Date());
        return student;
    }
}
