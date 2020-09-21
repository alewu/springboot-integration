package com.ale.mybatis.mapper;

import com.ale.mybatis.entity.Employee;
import com.ale.mybatis.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AutoInputTest {
    @Autowired
    private EmployeeService employeeService;

    @Test
    void testInsert() {
        Employee employee = new Employee();
        employee.setName("jack");
        employee.setEmail("jack@gmail.com");
        employeeService.save(employee);
        assertNotNull(employee.getGmtCreate());
    }

}
