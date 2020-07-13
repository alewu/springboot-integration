package com.ale.mybatis.mapper;

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
}
