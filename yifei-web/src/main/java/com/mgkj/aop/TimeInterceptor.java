package com.mgkj.aop;

import cn.hutool.core.date.StopWatch;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mgkj.annotation.Log;
import com.mgkj.entity.WmsLog;
import com.mgkj.service.WmsLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * 请求响应日志 AOP
 **/
@Aspect
@Component
@Slf4j
public class TimeInterceptor {

    @Resource
    private WmsLogService wmsLogService;

    @Value("${spring.application.name}")
    private String appName;

    private static final String TRACE_ID = "TRACE_ID";

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    @Around("execution(* com.mgkj.controller..*.*(..))")
    public Object doInterceptor(ProceedingJoinPoint point) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

        String requestId = UUID.randomUUID().toString();
        String url = request.getRequestURI();

        // 将请求参数转为 JSON
        Object[] args = point.getArgs();
        String reqJson;
        try {
            if (args != null && args.length == 1) {
                reqJson = objectMapper.writeValueAsString(args[0]);
            } else {
                reqJson = objectMapper.writeValueAsString(args);
            }
        } catch (Exception e) {
            reqJson = "[参数序列化失败：" + e.getMessage() + "]";
        }

        MDC.put(TRACE_ID, requestId);

        Object result = null;
        String resultJson = null;
        boolean hasLogAnnotation = false;
        String module = "";

        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof Log) {
                Log logAnno = (Log) annotation;
                module = logAnno.value();
                hasLogAnnotation = true;
                break;
            }
        }

        boolean success = true;
        Throwable targetException = null;

        try {
            // 执行目标方法
            result = point.proceed(args);
            try {
                resultJson = objectMapper.writeValueAsString(result);
            } catch (Exception e) {
                resultJson = "[结果序列化失败：" + e.getMessage() + "]";
            }
        } catch (Throwable t) {
            success = false;
            targetException = t;
            resultJson = "[执行异常]\n" + ExceptionUtils.getStackTrace(t);
        } finally {
            stopWatch.stop();
            long cost = stopWatch.getTotalTimeMillis();

            // 日志输出
            log.info("requestId={}, cost={}ms, path={}, params={}, result={}",
                    requestId, cost, url, reqJson, resultJson);

            if (!success) {
                log.error("requestId={}, 执行异常", requestId, targetException);
            }

            // 获取端口号
            int port = request.getLocalPort();
            String userField = appName + ":" + port;

            // 插入数据库
            if (hasLogAnnotation) {
                try {
                    WmsLog wmsLog = new WmsLog();
                    wmsLog.setRequestPath(url);
                    wmsLog.setRequestMethod(request.getMethod());
                    wmsLog.setSourceIp(request.getRemoteHost());
                    wmsLog.setServiceInfo(userField);
                    wmsLog.setRequestParams(reqJson);
                    wmsLog.setResponseBody(resultJson);
                    wmsLog.setDurationMs(cost);
                    wmsLog.setRemark(module);
                    wmsLogService.save(wmsLog);
                } catch (Exception e) {
                    log.error("requestId={}, 日志入库失败", requestId, e);
                }
            }

            MDC.remove(TRACE_ID);
        }

        if (!success) {
            throw targetException;
        }

        return result;
    }
}
