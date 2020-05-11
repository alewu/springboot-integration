package com.ale.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author alewu
 */
@Data
@NoArgsConstructor
//@AllArgsConstructor
public class ResponseResult<T> implements Serializable {
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
    private T data;

    @JsonIgnore
    private PageInfo pageInfo;

    public <T> ResponseResult(int i, String ok, T data) {}


    public static <T> ResponseResult<T> ok(T data) {
        return new ResponseResult<>(0, "ok", data);
    }

    public static <T> ResponseResult<T> ok(T data, String msg) {
        return new ResponseResult<>(0, msg, data);
    }


    public static <T> ResponseResult<T> failed(ExceptionEnum exceptionEnum) {
        return restResult(null, exceptionEnum);
    }

    private static <T> ResponseResult<T> restResult(T data, ExceptionEnum exceptionEnum) {
        ResponseResult<T> apiResult = new ResponseResult<>();
        apiResult.setData(data);
        apiResult.setCode(exceptionEnum.getCode());
        apiResult.setMsg(exceptionEnum.getMessage());
        return apiResult;
    }
}
