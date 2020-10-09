package com.ale.mybatis.mapper;

import com.ale.mybatis.entity.Employee;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author alewu
 * @date 2020/9/21
 */
@SpringBootTest
class PageTest {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    void test() {
        IPage<Employee> page = new Page<>(1, 2);
        LambdaQueryWrapper<Employee> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.eq(true, Employee::getName, "Jack");
        //        com.ale.mybatis.mapper.selectPage(page, lambdaQuery);
        IPage<Employee> userList = employeeMapper.selectPage(page, lambdaQuery);
        userList.getRecords().forEach(System.out::println);
        System.out.println(userList);

    }
}
