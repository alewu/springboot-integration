package com.ale.common.exception;

/**
 * 业务异常枚举类
 * @author alewu
 * @date 2018/3/13
 */
public enum BusinessExceptionEnum implements ExceptionEnum {
    /**
     * 资源未找到
     */
    RESOURCE_NOT_FOUND(500001, "resource not found");
    private final Integer code;
    private final String message;

    BusinessExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
