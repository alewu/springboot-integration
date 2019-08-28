package com.ale.common;

import lombok.Data;

import java.io.Serializable;

/**
  *  统一的响应
  * @author alewu
  * @date 2019/8/27
  */
@Data
public class GlobalResponse<T> implements Serializable {
    private String code;
    private String message;
    private T data;
}
