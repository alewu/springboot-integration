package com.ale.redisson;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class DelayedJob implements Serializable {
    private Integer id;
    private Integer delayTime;
    private String name;
}
