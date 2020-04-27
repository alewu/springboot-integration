package com.ale.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
/**
 * @author alewu
 */
@Data
@AllArgsConstructor
public class ResponseResult implements Serializable {
    /**
     * 返回的状态码
     */
    private Integer code;
    /**
     * 返回的信息
     */
    private String msg;
    /**
     * 返回的数据
     */
    private Object data;


}
