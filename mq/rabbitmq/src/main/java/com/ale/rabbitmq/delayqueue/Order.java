package com.ale.rabbitmq.delayqueue;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author alewu
 * @date 2020/7/14
 */
@Data
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer payStatus;
    private LocalDateTime orderTime;
}
