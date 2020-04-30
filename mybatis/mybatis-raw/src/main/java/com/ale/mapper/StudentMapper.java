package com.ale.mapper;

import com.ale.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * The interface Student mapper.
 * @author win10
 */
public interface StudentMapper  {

    /**
     * Add or update batch.
     *
     * @param students the students
     */
    void addOrUpdateBatch(@Param("list") List<Student> students);
}
