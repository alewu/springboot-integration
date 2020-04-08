package com.ale.mybatis;

import com.ale.mybatis.mapper.StudentMapper;
import com.ale.mybatis.entity.Student;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

@SpringBootTest
@MapperScan("com.ale.mybatis.mapper")
class MybatisApplicationTests {
    @Autowired
    private StudentMapper studentMapper;

    @Test
    void testInsert() {
        Student s = new Student();
        s.setName("ale");
        s.setSex(1);
        int insert = studentMapper.insert(s);
        System.out.println(insert);
    }

    @Test
    void contextLoads() {
        System.out.println(("----- selectAll method test ------"));
        List<Student> userList = studentMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    @Test
    void testEntityWrapperSelect() {
        Page<Student> page = new Page<>(1, 3);
        page = studentMapper.selectPage(page, null);
        page.getRecords().forEach(System.out::println);
    }


}
