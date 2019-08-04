package com.ale.config;


import org.apache.cxf.jaxrs.validation.ValidationExceptionMapper;

import javax.validation.ValidationException;
import javax.ws.rs.core.Response;
/**
  *  BeanValidation 校验异常响应处理类
  * @author alewu
  * @date 2019-08-02
  */
public class CustomBeanValidationExceptionMapper extends ValidationExceptionMapper {

    @Override
    public Response toResponse(ValidationException exception) {
        return Response.noContent().build();
    }
}
