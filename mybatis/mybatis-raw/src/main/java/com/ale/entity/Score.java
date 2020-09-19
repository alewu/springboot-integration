package com.ale.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 成绩表(Score)实体类
 *
 * @author alewu
 * @since 2020-09-19 11:51:17
 */
@Data
public class Score implements Serializable {
    private static final long serialVersionUID = 277489205743814757L;
    private Integer id;
    private Integer studentId;
    private Integer courseId;
    private Double scoreValue;
}