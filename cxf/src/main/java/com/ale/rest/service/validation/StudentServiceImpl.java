package com.ale.rest.service.validation;

import com.ale.domain.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 服务实现类
 *
 * @author alewu
 * @date 2019-08-02
 */
@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Override
    public Student getStudent(String name) {
        Student student = new Student();
        student.setName(name);
        student.setProfession("java developer");
        student.setBirthday(new Date());
        log.info("ok");
        return student;
    }

    @Override
    public Student addStudent(Student student) {
        return student;
    }
}
