package com.ale.mybatis.mapper;

import com.ale.mybatis.entity.Dept;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AutoInputTest {
    @Autowired
    private DeptMapper deptMapper;

    @Test
    void testInsert() {
        Dept dept = new Dept();
        dept.setDeptName("test1");
        deptMapper.insert(dept);
        assertNotNull(dept.getGmtCreate());
    }

}
