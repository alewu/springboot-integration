package com.ale.aspect;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;


/**
 * The type Log aspect.
 *
 * @author alewu
 */
@Slf4j
@Aspect
@Component
public class RequestLogAspect {

    @Pointcut("execution(public * com.ale.*.*Controller.*(..))")
    private void parameterPointCut() {
    }

    /**
     * 方法执行前，记录请求
     *
     * @param joinPoint the join point
     */
    @Before("parameterPointCut()")
    public void requestLog(JoinPoint joinPoint) {
        //这个RequestContextHolder是Spring mvc提供来获得请求的东西
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        String queryStr = request.getQueryString();
        if (StringUtils.isEmpty(queryStr)) {
            log.info("request uri: [{}] {}", request.getMethod(), request.getRequestURI());
        } else {
            log.info("request uri: [{}] {}?{} ", request.getMethod(), request.getRequestURI(), queryStr);
        }

        printRequestArgs(joinPoint);
    }

    private void printRequestArgs(JoinPoint joinPoint) {
        log.info("request method: {}", joinPoint.getSignature());


        Object[] reqArgs = joinPoint.getArgs();
        if (null == reqArgs) {
            return;
        }

        int index = 0;
        ObjectMapper mapper = new ObjectMapper();
        // 对文件上传做处理
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        for (Object arg : reqArgs) {
            if (Objects.isNull(arg)) {
                return;
            }
            if (arg instanceof MultipartFile) {
                return;
            }
            if (arg instanceof HttpServletRequest) {
                return;
            }
            try {
                log.info("请求入参[{}]: {}", index, mapper.writeValueAsString(arg));
            } catch (Exception ex) {
                log.error("请求入参转换异常", ex);
            }
            index++;
        }
    }


}
