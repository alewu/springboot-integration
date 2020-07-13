package com.ale.mybatis.mapper;

import com.ale.mybatis.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TransactionTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    @Transactional(rollbackFor = Exception.class)
    void testInsert() {
        User user = new User();
        user.setAge(12);
        user.setEmail("123@gmail");
        user.setName("test");
        userMapper.insert(user);
        Integer id = user.getId();
        int a = id / 0;
        assertEquals(155, id);
    }
}