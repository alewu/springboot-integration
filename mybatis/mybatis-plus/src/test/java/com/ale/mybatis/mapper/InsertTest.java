package com.ale.mybatis.mapper;


import com.ale.mybatis.entity.Dept;
import com.ale.mybatis.entity.Employee;
import com.ale.mybatis.service.EmployeeService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Slf4j
class InsertTest {
    @Autowired
    private EmployeeMapper userMapper;
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmployeeService userService;
    long start = 0l;

    @BeforeEach
    void before() {
        start = Instant.now().toEpochMilli();
    }

    @AfterEach
    void after() {
        long end = Instant.now().toEpochMilli();
        log.info("耗时{} ms", end - start);
    }

    @Test
    void testInsert() {
        for (int i = 0; i < 100; i++) {
            Employee user = new Employee();
            user.setId(55);
            user.setAge(12);
            user.setEmail("123@gmail");
            user.setName("test");
            userMapper.insert(user);
        }

        //        assertEquals(55, user.getId());
    }

    @Test
    void testInsert1() {
        Dept dept = new Dept();
        dept.setId(45);
        dept.setDeptName("IT");
        deptMapper.insert(dept);

        //        assertEquals(55, user.getId());
    }

    @Test
    void testBatchInsert() {
        List<Employee> users = Lists.newArrayList();
        for (int i = 0; i < 100; i++) {
            Employee user = new Employee();
            user.setAge(12 + i);
            user.setEmail("123@gmail");
            user.setName("test");
            users.add(user);
        }
        userService.saveBatch(users);
    }

    @Test
    void testSaveOrInsert() {
        Employee user = new Employee();
        user.setId(6);
        user.setAge(12);
        user.setEmail("123@gmail");
        user.setName("Jack");
        boolean saveOrUpdate = userService.saveOrUpdate(user, Wrappers.<Employee>lambdaUpdate().eq(Employee::getName,
                                                                                                   "Jack1"));
        assertTrue(saveOrUpdate);

    }
}
