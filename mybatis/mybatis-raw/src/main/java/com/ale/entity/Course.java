package com.ale.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 课程表(Course)实体类
 *
 * @author alewu
 * @since 2020-09-19 10:11:06
 */
@Data
public class Course implements Serializable {
    private static final long serialVersionUID = -93122075854310855L;
    private Integer id;
    /**
     * 课程名称
     */
    private String courseName;
}