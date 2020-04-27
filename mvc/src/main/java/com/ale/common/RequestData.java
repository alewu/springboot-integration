package com.ale.common;

import com.ale.exception.ValidateCodeException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Optional;


@ApiModel("不带分页信息的请求入参")
@Slf4j
public class RequestData<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2423424971495214211L;

    /**
     * 封装请求的入参
     */
    @ApiModelProperty("请求入参")
    private T params;


    public T getParams() {
        return params;
    }

    public void setParams(T params) {
        try {
            Optional.ofNullable(params).orElseThrow(() -> new ValidateCodeException("参数异常"));
        } catch (ValidateCodeException e) {
            log.error("RequestData setParams failed", e);
        }
        this.params = params;
    }

    public RequestData() {
    }

    public RequestData(T params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "RequestData{" +
                "params=" + params +
                '}';
    }
}