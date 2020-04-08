package com.ale.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Student {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer sex;
    private Integer age;
}
