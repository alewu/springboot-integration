package com.ale.bean;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author alewu
 * @date 2020/9/3
 */
@Data
public class User implements Serializable {
    private Integer id;
    private String name;
    private LocalDateTime birthday;
    private String nickname;
}
