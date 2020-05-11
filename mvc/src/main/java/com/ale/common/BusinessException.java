package com.ale.common;

/**
  *  业务异常
  * @author alewu
  * @date 2019/8/28
  */
public class BusinessException extends RuntimeException implements ExceptionEnum {
	private final BusinessExceptionEnum businessExceptionEnum = null;
	private final Integer code;
	private final String message;

	public BusinessException(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public BusinessException(BusinessExceptionEnum businessExceptionEnum) {
		this.code = businessExceptionEnum.getCode();
		this.message = businessExceptionEnum.getMessage();
	}

	@Override
	public Integer getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public BusinessExceptionEnum getBusinessExceptionEnum() {
		return businessExceptionEnum;
	}
}
