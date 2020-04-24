package com.ale.mybatis.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class DeptMapperTest {
    @Autowired
    private DeptMapper deptMapper;
    @Test
    public void testDeleteByMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("dept_name", "it");
        deptMapper.deleteByMap(map);
    }

}