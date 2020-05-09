package com.ale.exception;

/**
 * The enum Response code enum.
 */
public enum ResponseCodeEnum {
    /**
     * 成功返回的状态码
     */
    SUCCESS(10000, "success"),
    /**
     * 资源不存在的状态码
     */
    RESOURCES_NOT_EXIST(10001, "资源不存在"),
    /**
     * 所有无法识别的异常默认的返回状态码
     */
    SERVICE_ERROR(50000, "服务器异常");
    /**
     * 状态码
     */
    private final int code;
    /**
     * 返回信息
     */
    private final String msg;

    ResponseCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * Gets msg.
     *
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }
}
