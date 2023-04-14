package com.ale.mybatis.mapper;


import com.ale.mybatis.entity.Employee;
import com.ale.mybatis.service.EmployeeService;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;

@SpringBootTest
@Slf4j
class UpdateTest {
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
    void testUpdate() {
        Employee user = new Employee();
        user.setId(55);
        user.setAge(12);
        user.setEmail("123@gmail");
        user.setName("test");
        LambdaUpdateWrapper<Employee> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(Employee::getName, "14");
        updateWrapper.eq(Employee::getId, 55);
        userMapper.update(user, updateWrapper);
        //        assertEquals(55, user.getId());
    }
}
