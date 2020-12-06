package com.ale.aop.aspect;

import com.ale.aop.bean.BaseDoor;
import com.ale.aop.bean.IronDoor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author alewu
 * @date 2020/6/19
 */
@Slf4j
@Aspect
@Component
public class DemoAspect {

    @Pointcut("execution(* com.ale.aop.service.impl.AopDemoServiceImpl.*(..))")
    private void pointCut() {
        // 切入点
    }


    /**
     * 方法执行前，记录请求
     *
     * @param joinPoint the join point
     */
    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        BaseDoor arg = (BaseDoor) args[0];
        arg.setColor("red");
        arg.setSize(1);
    }

    /**
     * 方法执行后，记录请求
     *
     * @param joinPoint the join point
     */
    @AfterReturning(value = "pointCut()", returning = "result")
    public void after(JoinPoint joinPoint, Object result) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            log.info("arg: {}", arg);
        }
        if (result instanceof IronDoor) {
            IronDoor a = (IronDoor) result;
            log.info("door color: {}", a.getColor());
            a.setColor("green");
        }
    }
}
