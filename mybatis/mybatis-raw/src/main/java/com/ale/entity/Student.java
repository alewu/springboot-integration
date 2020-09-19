package com.ale.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author alewu
 * @date 2020/9/14
 */
@Data
public class Student implements Serializable {
    private Integer id;
    private String name;
    private Integer sex;
    private LocalDate birthday;
}
