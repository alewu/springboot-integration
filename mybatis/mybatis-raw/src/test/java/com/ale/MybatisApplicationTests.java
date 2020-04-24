package com.ale;


import com.ale.mapper.StudentMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MapperScan("com.ale.mybatis.com.ale.mybatis.mapper")
class MybatisApplicationTests {
    @Autowired
    private StudentMapper studentMapper;



}
