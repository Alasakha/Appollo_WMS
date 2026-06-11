package com.mgkj.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mgkj.annotation.Debounce;
import com.mgkj.common.result.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class DebounceAspect {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Around("@annotation(com.mgkj.annotation.Debounce)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        Debounce debounce = method.getAnnotation(Debounce.class);

        long intervalMillis = debounce.unit().toMillis(debounce.interval());

        // 生成唯一key：方法名 + 参数hash
        String path = method.getDeclaringClass().getName() + "." + method.getName();
        String argsJson = objectMapper.writeValueAsString(pjp.getArgs());
        String key = path + ":" + argsJson.hashCode();

        if (DebounceCache.isDuplicate(key, intervalMillis)) {
            // 拦截重复请求
            return Result.fail().message(
                    String.format("重复请求,请%d秒后再试", debounce.interval())
            );
        }

        // 放行执行原方法
        return pjp.proceed();
    }
}
