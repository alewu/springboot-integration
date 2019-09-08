package com.ale.config;


import org.apache.cxf.common.logging.LogUtils;
import org.apache.cxf.jaxrs.utils.JAXRSUtils;
import org.apache.cxf.jaxrs.validation.ValidationExceptionMapper;
import org.apache.cxf.validation.ResponseConstraintViolationException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
  *  BeanValidation 校验异常响应处理类
  * @author alewu
  * @date 2019-08-02
  */
@Provider
public class CustomBeanValidationExceptionMapper extends ValidationExceptionMapper {
    private static final Logger LOG = LogUtils.getL7dLogger(ValidationExceptionMapper.class);
    private boolean addMessageToResponse;
    @Override
    public Response toResponse(ValidationException exception) {
        Response.Status errorStatus = Response.Status.INTERNAL_SERVER_ERROR;
        if (exception instanceof ConstraintViolationException) {

            StringBuilder responseBody = addMessageToResponse ? new StringBuilder() : null;

            final ConstraintViolationException constraint = (ConstraintViolationException) exception;

            for (final ConstraintViolation< ? > violation: constraint.getConstraintViolations()) {
                String message = buildErrorMessage(violation);
                if (responseBody != null) {
                    responseBody.append(message).append('\n');
                }
                LOG.log(Level.WARNING, message);
            }

            if (!(constraint instanceof ResponseConstraintViolationException)) {
                errorStatus = Response.Status.BAD_REQUEST;
            }
            return buildResponse(errorStatus, responseBody != null ? responseBody.toString() : null);
        }
        return buildResponse(errorStatus, addMessageToResponse ? exception.getMessage() : null);
    }

    @Override
    protected String buildErrorMessage(ConstraintViolation<?> violation) {
        return "Value "
                + (violation.getInvalidValue() != null ? "'" + violation.getInvalidValue().toString() + "'" : "(null)")
                + " of " + violation.getRootBeanClass().getSimpleName()
                + "." + violation.getPropertyPath()
                + ": " + violation.getMessage();
    }

    @Override
    protected Response buildResponse(Response.Status errorStatus, String responseText) {
        Response.ResponseBuilder rb = JAXRSUtils.toResponseBuilder(errorStatus);
        if (responseText != null) {
            rb.type(MediaType.APPLICATION_JSON).entity(responseText);
        }
        return rb.build();
    }

}
