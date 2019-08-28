package com.ale.common.exception.custom;
/**
  * 未授权异常
  * @author alewu
  * @date 2018/2/6
  */
public class UnAuthorizedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UnAuthorizedException(){
        super();
    }

    public UnAuthorizedException(String message){
        super(message);
    }
    
}
