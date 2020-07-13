package com.ale.mybatis.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * @author alewu
 * @date 2020/7/13
 */
class DeleteTest {
    @Autowired
    private DeptMapper deptMapper;

    @Test
    void testDeleteByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("dept_name", "it");
        deptMapper.deleteByMap(map);
    }
}
