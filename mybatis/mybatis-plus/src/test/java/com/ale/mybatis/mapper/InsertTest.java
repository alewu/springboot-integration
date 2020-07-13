package com.ale.mybatis.mapper;


import com.ale.mybatis.entity.User;
import com.ale.mybatis.service.UserService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class InsertTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @Test
    void testInsert() {
        User user = new User();
        user.setId(55);
        user.setAge(12);
        user.setEmail("123@gmail");
        user.setName("test");
        userMapper.insert(user);
        assertEquals(55, user.getId());
    }

    @Test
    void testBatchInsert() {
        List<User> users = Lists.newArrayList();
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setAge(12 + i);
            user.setEmail("123@gmail");
            user.setName("test");
            users.add(user);
        }
        userService.saveBatch(users);
    }

    @Test
    void testSaveOrInsert() {
        User user = new User();
        user.setId(6);
        user.setAge(12);
        user.setEmail("123@gmail");
        user.setName("Jack");
        boolean saveOrUpdate = userService.saveOrUpdate(user, Wrappers.<User>lambdaUpdate().eq(User::getName, "Jack1"));
        assertTrue(saveOrUpdate);

    }
}
