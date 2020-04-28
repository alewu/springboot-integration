package com.ale.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * The type Base exception.
 * @author win10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseException extends RuntimeException{

    /**
     * 枚举对象
     */
    private ResponseCodeEnum code;

    /**
     * Instantiates a new Base exception.
     *
     * @param code the code
     */
    public BaseException(ResponseCodeEnum code) {
        this.code = code;
    }

    /**
     * Instantiates a new Base exception.
     *
     * @param cause the cause
     * @param code  the code
     */
    public BaseException(Throwable cause, ResponseCodeEnum code) {
        super(cause);
        this.code = code;
    }
}
