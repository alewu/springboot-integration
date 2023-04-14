package com.ale.mapper;

import com.ale.entity.StudentQuery;
import com.ale.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Slf4j
@SpringBootTest
class QueryTest {
    @Autowired
    private StudentMapper studentMapper;


    @Test
     void testQuery() {
        StudentQuery query = new StudentQuery();
        query.setIds(Arrays.asList(1));
        query.setName("");
        Date createTime = new Date();
        log.info("" + createTime.getTime());
        query.setCreateTime(createTime);
        List<Student> students = studentMapper.listStudent(query);
        for (Student student : students) {
            System.out.println(student);
        }

    }

}