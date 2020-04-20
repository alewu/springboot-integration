package com.ale;

import com.ale.entity.User;
import com.ale.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class QueryWrapperTests {
    @Autowired
    private UserMapper mapper;

    /**
     * <p>
     * 根据 entity 条件，查询列表,
     * 这里和上方删除构造条件一样，只是seletOne返回的是一条实体记录，当出现多条时会报错
     * </p>
     */
    @Test
    public void selectOne() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(true,"name", "keep");

        List<User> user = mapper.selectList(queryWrapper);

        System.out.println(user);
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
//        mapper.selectPage(page, lambdaQuery);
        IPage<User> userList = mapper.selectPage(page, lambdaQuery);
//        userList.getRecords().forEach(System.out::println);
        System.out.println(userList);

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
        IPage<User> userIPage = mapper.selectPage(page, queryWrapper);
        System.out.println(userIPage);
    }


}
