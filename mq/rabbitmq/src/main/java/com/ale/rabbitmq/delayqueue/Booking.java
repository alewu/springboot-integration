package com.ale.rabbitmq.delayqueue;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
/**
  *
  * @author alewu
  * @date 2020/7/13
  */
@Data
public class Booking implements Serializable {
    private static final long serialVersionUID = 1L;

    private String bookingName;
    private LocalDateTime bookingTime;
    private String author;
}
