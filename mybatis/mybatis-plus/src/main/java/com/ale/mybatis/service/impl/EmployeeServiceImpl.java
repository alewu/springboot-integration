package com.ale.mybatis.service.impl;

import com.ale.mybatis.entity.Employee;
import com.ale.mybatis.mapper.EmployeeMapper;
import com.ale.mybatis.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {


    @Override
    public void saveEmployeeBatch(List<Employee> users) {
        Employee employee = new Employee();
        employee.setAge(45);
        employee.setEmail("sss@gmail.com");
        employee.setName("jack");
        this.saveBatch(Collections.singletonList(employee));
    }
}
