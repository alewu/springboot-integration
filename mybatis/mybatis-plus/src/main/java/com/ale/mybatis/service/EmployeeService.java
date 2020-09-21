package com.ale.mybatis.service;

import com.ale.mybatis.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * The interface User service.
 */
public interface EmployeeService extends IService<Employee> {

    /**
     * Save user batch.
     *
     * @param users the users
     */
    void saveEmployeeBatch(List<Employee> users);
}
