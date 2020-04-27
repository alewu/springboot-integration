package com.ale.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class BaseException extends RuntimeException{

    /**
     * 枚举对象
     */
    private ResponseCode code;

    public BaseException(ResponseCode code) {
        this.code = code;
    }

    public BaseException(Throwable cause, ResponseCode code) {
        super(cause);
        this.code = code;
    }
}
