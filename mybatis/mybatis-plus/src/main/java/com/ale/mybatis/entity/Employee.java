package com.ale.mybatis.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author ale
 */
@Data
@TableName("employee")
public class Employee {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer age;
    private String email;
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;
}
