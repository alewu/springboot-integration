package com.ale.mapper;

import com.ale.entity.Student;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class StudentMapperTest {
    @Autowired
    private StudentMapper studentMapper;


    @Test
    public void testAddOrUpdateBatch() {
        List<Student> students = Lists.newArrayList();
        Student student = new Student();
        student.setName("java");
        student.setBirthday(LocalDate.now());
        student.setSex(11);
        Student student1 = new Student();
        student1.setName("python");
        student1.setBirthday(LocalDate.now());
        student1.setSex(1);
        Student student2 = new Student();
        student2.setName("c");
        student2.setBirthday(LocalDate.now());
        student2.setSex(2);
        students.add(student);
        students.add(student1);
        students.add(student2);
        studentMapper.addOrUpdateBatch(students);

    }

}