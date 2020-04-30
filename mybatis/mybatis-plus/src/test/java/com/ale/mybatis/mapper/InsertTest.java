package com.ale.mybatis.mapper;


import com.ale.mybatis.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class InsertTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert(){
        User user = new User();
        user.setId(55);
        user.setAge(12);
        user.setEmail("123@gmail");
        user.setName("test");
        userMapper.insert(user);
        assertEquals(55, user.getId());
    }

    @Test
    public void testSaveOrInsert(){
        User user = new User();
        user.setId(55);
        user.setAge(12);
        user.setEmail("123@gmail");
        user.setName("test");

    }
}
