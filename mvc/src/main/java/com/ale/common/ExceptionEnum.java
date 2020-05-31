package com.ale.common;
/**
  *  异常枚举接口
  * @author alewu
  * @date 2019/8/28
  */
public interface ExceptionEnum {
    /**
     * 获取业务码
     * @return 业务码
     */
    Integer getCode();

    /**
     * 获取业务消息
     * @return 业务消息
     */
    String getMessage();

}
