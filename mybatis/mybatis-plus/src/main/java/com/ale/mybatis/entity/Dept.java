package com.ale.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

/**
 * @author alewu
 * @date 2020/7/13
 */
@Data
public class Dept extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String deptName;

    @TableLogic
    private Integer deleted;
}
