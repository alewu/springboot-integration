package com.ale.event;

import lombok.Data;

/**
 * 下单事件
 * @author alewu
 */
@Data
public class OrderCreatedEvent {
    private Long id;
    private String userName;
    private String goodsName;
}
