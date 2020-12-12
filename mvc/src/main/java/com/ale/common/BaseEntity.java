package com.ale.common;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;
/**
  *
  * @author alewu
  */
@Data
public class BaseEntity {
    /**
     * 创建日期
     */
    @JsonIgnore
    @TableField(value = "gmt_create",fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    /**
     * 更新日期
     */
    @JsonIgnore
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;

    /**
     * 创建人
     */
    @JsonIgnore
    @TableField(value = "created_by", fill = FieldFill.INSERT)
    private Integer createdBy;

    /**
     * 更新人
     */
    @JsonIgnore
    @TableField(value = "modified_by", fill = FieldFill.INSERT_UPDATE)
    private Integer modifiedBy;
}