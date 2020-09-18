package com.ale.mapper;

import com.ale.cache.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Student mapper.
 * @author alewu
 */
@Repository
public interface StudentMapper  {

    /**
     * Add or update batch.
     *
     * @param students the students
     */
    void addOrUpdateBatch(@Param("list") List<Student> students);
}
