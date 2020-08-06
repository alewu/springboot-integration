package com.ale.cache.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author alewu
 */
@Data
@TableName("tb_order")
public class Order {
    private Long id;
    private String userName;
    private String goodsName;
}
