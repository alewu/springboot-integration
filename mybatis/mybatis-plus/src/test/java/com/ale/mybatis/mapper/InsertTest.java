package com.ale.mybatis.mapper;


import com.ale.mybatis.entity.User;
import com.ale.mybatis.service.UserService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class InsertTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @Test
    public void testInsert() {
        User user = new User();
        user.setId(55);
        user.setAge(12);
        user.setEmail("123@gmail");
        user.setName("test");
        userMapper.insert(user);
        assertEquals(55, user.getId());
    }

    @Test
    public void testSaveOrInsert() {
        User user = new User();
        user.setId(6);
        user.setAge(12);
        user.setEmail("123@gmail");
        user.setName("Jack");
        boolean saveOrUpdate = userService.saveOrUpdate(user, Wrappers.<User>lambdaUpdate().eq(User::getName, "Jack1"));
        assertTrue(saveOrUpdate);

    }
}
