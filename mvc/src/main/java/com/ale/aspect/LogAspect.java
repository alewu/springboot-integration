package com.ale.aspect;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;

import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * The type Log aspect.
 *
 * @author win10
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    @Pointcut("execution(public * com.ale.controller.*.*(..))")
    private void parameterPointCut() {
        log.info("切入点:{}", "com.ale.controller");
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
        if (StringUtils.isBlank(queryStr)) {
            log.info("request uri: [{}] {}", request.getMethod(), request.getRequestURI());
        } else {
            log.info("request uri: [{}] {}?{} ", request.getMethod(), request.getRequestURI(), queryStr);
        }

        printRequestArgs(joinPoint);
    }

    private void printRequestArgs(JoinPoint joinPoint) {
        log.info("请求方法: {}", joinPoint.getSignature());


        Object[] reqArgs = joinPoint.getArgs();
        if (null == reqArgs) {
            return;
        }

        int index = 0;
        ObjectMapper mapper = new ObjectMapper();
        for (Object arg : reqArgs) {
            try {
                log.info("请求入参[{}]: {}", index, mapper.writeValueAsString(arg));
            } catch (Exception ex) {
                log.error("请求入参转换异常", ex);
            }
            index++;
        }
    }


}
