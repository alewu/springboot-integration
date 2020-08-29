package com.ale.mybatis.mapper;


import com.ale.mybatis.entity.User;
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
public class QueryWrapperTest {
    @Autowired
    private UserMapper userMapper;


    @Test
    public void selectOne() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "Jack");
        User user = userMapper.selectOne(queryWrapper);
        assertEquals("Jack", user.getName());
    }

    /**
     * <p>
     * 根据 entity 条件，查询列表 ,按条件分页查询
     * </p>
     */
    @Test
    public void selectList() {

        IPage<User> page = new Page<>(1, 2);
        //        Wrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //    LambdaQueryWrapper<User> lambda = new QueryWrapper<User>().lambda();
        //    LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.eq(true, User::getName, "it");
        //        com.ale.mybatis.mapper.selectPage(page, lambdaQuery);
        IPage<User> userList = userMapper.selectPage(page, lambdaQuery);
        //        userList.getRecords().forEach(System.out::println);
        System.out.println(userList);

    }

    @Test
    void testSelectList() {
        // 查询全部
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user.getName());
        }
    }


    /**
     * <p>
     * 根据 entity 条件，查询全部记录（并翻页）
     * </p>
     */
    @Test
    public void selectPage() {
        Page<User> page = new Page<>(1, 5);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "java");
        IPage<User> userIPage = userMapper.selectPage(page, queryWrapper);
        System.out.println(userIPage);
    }


}
