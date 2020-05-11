package com.ale.aspect;

import com.ale.common.BusinessException;
import com.ale.common.BusinessExceptionEnum;
import com.ale.common.ResponseResult;
import com.baomidou.mybatisplus.extension.api.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.lang.reflect.Field;
import java.util.Set;

/**
 * @author alewu
 */
@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler({RuntimeException.class,Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R exception(Exception e) {
        log.error("全局异常信息 ex={}", e.getMessage(), e);
        return R.failed(e.getLocalizedMessage());
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseResult<String> exception(BusinessException e) {
        log.error("全局异常信息 ex={}", e.getMessage(), e);
        return ResponseResult.failed(e.getBusinessExceptionEnum());
    }

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

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseResult<String> handleConstraintViolationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        String messageTemplate = constraintViolations.iterator().next().getMessageTemplate();
        log.warn(messageTemplate);
        return ResponseResult.failed(BusinessExceptionEnum.FILE_UPLOAD_EXCEPTION);
    }
}
