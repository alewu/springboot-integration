package com.ale.mybatis.mapper;

import com.ale.mybatis.entity.Dept;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LogicDeletedTest {
    @Autowired
    private DeptMapper deptMapper;

    @Test
    void testDeleteById() {
        Dept dept = new Dept();
        dept.setDeptName("IT");
        deptMapper.insert(dept);
        deptMapper.deleteById(dept.getId());
    }
}
