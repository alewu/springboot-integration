package com.ale.config;

import com.ale.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.jaxrs.utils.JAXRSUtils;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


/**
 * 业务异常处理类
 *
 * @author alewu
 * @date 2019/8/28
 */
@Slf4j
@Provider
public class ServiceExceptionMapper implements ExceptionMapper<BusinessException> {

    @Override
    public Response toResponse(BusinessException exception) {
        Response.Status errorStatus = Response.Status.INTERNAL_SERVER_ERROR;

        StringBuilder responseBody = new StringBuilder();

        String message = buildErrorMessage(exception);
        if (responseBody != null) {
            responseBody.append(message).append('\n');
        }

        return buildResponse(errorStatus, responseBody != null ? responseBody.toString() : null);

    }

    protected String buildErrorMessage(BusinessException exception) {
        return exception.getMessage();
    }

    protected Response buildResponse(Response.Status errorStatus, String responseText) {
        Response.ResponseBuilder rb = JAXRSUtils.toResponseBuilder(errorStatus);
        if (responseText != null) {
            rb.type(MediaType.APPLICATION_JSON).entity(responseText);
        }
        return rb.build();
    }


}
