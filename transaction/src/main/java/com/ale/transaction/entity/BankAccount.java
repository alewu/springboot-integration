package com.ale.transaction.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author alewu
 * @date 2020/8/25
 */
@Data
@TableName("bank_account")
public class BankAccount {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField(value = "full_name")
    private String fullName;
    @TableField(value = "balance")
    private BigDecimal balance;
}
