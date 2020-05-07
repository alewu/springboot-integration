package com.ale.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_order")
public class Order {
    private Long id;
    private String userName;
    private String goodsName;
}
