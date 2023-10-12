package com.ale.mybatis.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author alewu
 * @date 2020/7/13
 */
@Data
public class Dept {
    @TableId(type = IdType.INPUT)
    private Integer id;

    private String deptName;

//    @TableLogic
//    private Integer deleted;

    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;
}
