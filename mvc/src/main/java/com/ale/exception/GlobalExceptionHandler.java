package com.ale.exception;

import com.ale.common.ResponseResult;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.xml.bind.ValidationException;
import java.util.StringJoiner;

/**
 * The type Global exception handler.
 *
 * @author ale  规范：流程跳转尽量避免使用抛 BizException 来做控制。
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 处理未捕获的Exception
     *
     * @param e 异常
     * @return 统一响应体  data:null
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult handleException(Exception e) {
        log.error(e.getMessage(), e);
        return new ResponseResult(ResponseCodeEnum.SERVICE_ERROR.getCode(), ResponseCodeEnum.SERVICE_ERROR.getMsg(),
                                  null);
    }

    /**
     * 处理未捕获的RuntimeException
     *
     * @param e 运行异常
     * @return 统一响应体  data:null
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseResult handleRuntimeException(RuntimeException e) {
        log.error(e.getMessage(), e);
        return new ResponseResult(ResponseCodeEnum.SERVICE_ERROR.getCode(), ResponseCodeEnum.SERVICE_ERROR.getMsg(),
                                  null);
    }

    /**
     * 处理业务异常BaseException
     *
     * @param e 业务异常
     * @return 统一响应体  data:null
     */
    @ExceptionHandler(BaseException.class)
    public ResponseResult handleBaseException(BaseException e) {
        log.error(e.getMessage(), e);
        ResponseCodeEnum code = e.getCode();
        return new ResponseResult(code.getCode(), code.getMsg(), null);
    }

    /**
     * 参数校验错误
     *
     * @param ex the ex
     * @return response result
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseResult handleValidationException(ValidationException ex) {
        return new ResponseResult(ResponseCodeEnum.RESOURCES_NOT_EXIST.getCode(), ex.getCause().getMessage(), null);
    }

    /**
     * 字段校验不通过异常
     *
     * @param ex the ex
     * @return response result
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        StringJoiner sj = new StringJoiner(";");
        ex.getBindingResult().getFieldErrors().forEach(x -> sj.add(x.getDefaultMessage()));
        return new ResponseResult(ResponseCodeEnum.SERVICE_ERROR.getCode(), sj.toString(), null);
    }

    /**
     * Controller参数绑定错误
     *
     * @param ex the ex
     * @return response result
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseResult handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        return new ResponseResult(ResponseCodeEnum.SERVICE_ERROR.getCode(), ex.getMessage(), null);
    }

    /**
     * 处理方法不支持异常
     *
     * @param ex the ex
     * @return response result
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseResult handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return new ResponseResult(ResponseCodeEnum.SERVICE_ERROR.getCode(), ex.getMessage(), null);
    }

}
