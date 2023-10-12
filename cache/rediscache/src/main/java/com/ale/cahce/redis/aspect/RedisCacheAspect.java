package com.ale.cahce.redis.aspect;

import com.ale.cahce.redis.service.RedisCacheService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wywuj
 */
@Aspect
@Component
@Slf4j
@AllArgsConstructor
public class RedisCacheAspect {
    private final RedisCacheService redisCacheService;

    @Pointcut("execution(* com.ale.cahce.redis.service.*.*(..)) && @annotation(com.ale.cahce.redis.aspect.RedisCache)")
    public void pointCut() {
    }

//    @Around(value = "pointCut();")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        if (!(signature instanceof MethodSignature)) {
            return null;
        }
        MethodSignature methodSig = (MethodSignature) joinPoint.getSignature();
        Method method = methodSig.getMethod();
        Class returnType = methodSig.getReturnType();
        Class superclass = returnType.getSuperclass();
        List<Class<?>> classes = getsuperClass(returnType);
        Class[] interfaces = returnType.getInterfaces();
        Annotation[][] annotations = method.getParameterAnnotations();

        List<Integer> listPos = new ArrayList<>();
        for (int i = 0; i < annotations.length; i++) {
            for (Annotation annotation : annotations[i]) {
                if (annotation instanceof RedisCache) {
                    listPos.add(i);
                    break;
                }
            }
        }
        if (listPos.size() <= 0) {
            throw new NoSuchMethodException("could find the annotation RedisLockParam when use the annotation RedisLock 1");
        }
        Object[] args = joinPoint.getArgs();
        List<String> paramList = new ArrayList<>();
        for (int i = 0; i < listPos.size(); i++) {
            Object arg = args[listPos.get(i)];
            if (arg instanceof String) {
                paramList.add(arg.toString());
            } else {
                Field[] fs = arg.getClass().getDeclaredFields();
                boolean find = false;
                for (Field f : fs) {
                    if (f.isAnnotationPresent(RedisCache.class)) {
                        f.setAccessible(true);
                        paramList.add(f.get(arg).toString());
                        find = true;
                    }
                }
                if (!find) {
                    throw new NoSuchMethodException("could find the annotation RedisLockParam when use the annotation RedisLock 2");
                }
            }
        }
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            throw e;
        } finally {

        }
    }

    public static List<Class<?>> getsuperClass(Class<?> calzz){
        List<Class<?>> listSuperClass = new ArrayList<Class<?>>();
        Class<?> superclass = calzz.getSuperclass();
        while (superclass != null) {
            if(superclass.getName().equals("java.lang.Object")){
                break;
            }
            listSuperClass.add(superclass);
            superclass = superclass.getSuperclass();
        }
        return listSuperClass;
    }
}
