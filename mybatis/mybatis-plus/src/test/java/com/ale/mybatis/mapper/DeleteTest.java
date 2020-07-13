package com.ale.mybatis.mapper;

import com.ale.mybatis.entity.User;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author alewu
 * @date 2020/7/13
 */
@SpringBootTest
class DeleteTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void testDeleteByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("age", 12);
        int i = userMapper.deleteByMap(map);
        Assertions.assertTrue(i > 0);
    }

    @Test
    void testDeleteBy() {
        int i = userMapper.delete(Wrappers.<User>lambdaQuery().ge(User::getAge, 10));
        Assertions.assertTrue(i > 0);
    }
}
