package com.ale.mapper;

import com.ale.entity.StudentQuery;
import com.ale.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Student mapper.
 *
 * @author alewu
 */
@Repository
public interface StudentMapper {

    /**
     * Add or update batch.
     *
     * @param students the students
     */
    void addOrUpdateBatch(@Param("list") List<Student> students);

    /**
     * Gets one.
     *
     * @param studentId the student id
     * @return the one
     */
    Student getOne(@Param("studentId") Integer studentId);

    List<Student> listStudent(StudentQuery query);
}
