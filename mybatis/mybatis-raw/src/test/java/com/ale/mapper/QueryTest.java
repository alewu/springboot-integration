package com.ale.mapper;

import com.ale.entity.StudentQuery;
import com.ale.entity.Student;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class QueryTest {
    @Autowired
    private StudentMapper studentMapper;


    @Test
     void testQuery() {
        StudentQuery query = new StudentQuery();
        query.setIds(Arrays.asList(1));
        query.setName("");
        List<Student> students = studentMapper.listStudent(query);
        for (Student student : students) {
            System.out.println(student);
        }

    }

}