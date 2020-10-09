package com.ale.mybatis.mapper;


import com.ale.mybatis.entity.Employee;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class QueryWrapperTest {
    @Autowired
    private EmployeeMapper employeeMapper;


    @Test
    void selectOne() {
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "Jack");
        Employee user = employeeMapper.selectOne(queryWrapper);
        assertEquals("Jack", user.getName());
    }

    /**
     * <p>
     * 根据 entity 条件，查询列表 ,按条件分页查询
     * </p>
     */
    @Test
    void selectList() {

        IPage<Employee> page = new Page<>(1, 2);
        //        Wrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //    LambdaQueryWrapper<User> lambda = new QueryWrapper<User>().lambda();
        //    LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Employee> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.eq(true, Employee::getName, "it");
        //        com.ale.mybatis.mapper.selectPage(page, lambdaQuery);
        IPage<Employee> userList = employeeMapper.selectPage(page, lambdaQuery);
        //        userList.getRecords().forEach(System.out::println);
        System.out.println(userList);

    }

    @Test
    void testSelectList() {
        // 查询全部
        List<Employee> users = employeeMapper.selectList(null);
        for (Employee user : users) {
            System.out.println(user.getName());
        }
    }


    /**
     * <p>
     * 根据 entity 条件，查询全部记录（并翻页）
     * </p>
     */
    @Test
    void selectPage() {
        Page<Employee> page = new Page<>(1, 5);
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "java");
        IPage<Employee> userIPage = employeeMapper.selectPage(page, queryWrapper);
        System.out.println(userIPage);
    }


}
