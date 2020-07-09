package com.ale.exception;

import com.ale.common.BusinessExceptionEnum;
import com.ale.common.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.lang.reflect.Field;
import java.util.Set;

/**
 * The type Global exception handler.
 * 1.处理自定义异常
 * 2.未知异常统一返回服务器错误
 *
 * @author ale 规范：流程跳转尽量避免使用抛 BizException 来做控制。
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 处理未捕获的Exception
     *
     * @param e 异常
     * @return 统一响应体 data:null
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult<String> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseResult.failed(ResponseCodeEnum.SERVICE_ERROR.getCode(), ResponseCodeEnum.SERVICE_ERROR.getMsg());
    }

    /**
     * 处理未捕获的RuntimeException
     *
     * @param e 运行异常
     * @return 统一响应体 data:null
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseResult<String> handleRuntimeException(RuntimeException e) {
        log.error(e.getMessage(), e);
        return ResponseResult.failed(ResponseCodeEnum.SERVICE_ERROR.getCode(), ResponseCodeEnum.SERVICE_ERROR.getMsg());
    }

    /**
     * 处理业务异常BaseException
     *
     * @param e 业务异常
     * @return 统一响应体 data:null
     */
    @ExceptionHandler(BaseException.class)
    public ResponseResult<String> handleBaseException(BaseException e) {
        log.error(e.getMessage(), e);
        ResponseCodeEnum code = e.getCode();
        return ResponseResult.failed(code.getCode(), code.getMsg());
    }

    /**
     * 参数校验错误
     *
     * @param e the e
     * @return response result
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseResult<String> handleConstraintViolationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        String messageTemplate = constraintViolations.iterator().next().getMessageTemplate();
        log.warn(messageTemplate);
        return ResponseResult.failed(400, messageTemplate);
    }

    /**
     * 字段校验不通过异常
     *
     * @param e the e
     * @return response result
     * @throws NoSuchFieldException the no such field exception
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) throws NoSuchFieldException {
        // 从异常对象中拿到错误信息
        String defaultMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        // 参数的Class对象，等下好通过字段名称获取Field对象
        Class<?> parameterType = e.getParameter().getParameterType();
        // 拿到错误的字段名称
        String fieldName = e.getBindingResult().getFieldError().getField();
        Field field = parameterType.getDeclaredField(fieldName);
        // 获取Field对象上的自定义注解
        //        ExceptionCode annotation = field.getAnnotation(ExceptionCode.class);
        //
        //        // 有注解的话就返回注解的响应信息
        //        if (annotation != null) {
        //            return new ResponseResult<>(annotation.value(),annotation.message(),defaultMessage);
        //        }

        // 没有注解就提取错误提示信息进行返回统一错误码
        return ResponseResult.failed(BusinessExceptionEnum.RESOURCE_NOT_FOUND);
    }

    /**
     * Controller参数绑定错误
     *
     * @param ex the ex
     * @return response result
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseResult<String> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        return ResponseResult.failed(ResponseCodeEnum.SERVICE_ERROR.getCode(), ex.getMessage());
    }

    /**
     * 处理方法不支持异常
     *
     * @param ex the ex
     * @return response result
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseResult<String> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return ResponseResult.failed(ResponseCodeEnum.SERVICE_ERROR.getCode(), ex.getMessage());
    }

}
