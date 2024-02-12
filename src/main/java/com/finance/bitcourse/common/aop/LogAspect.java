package com.finance.bitcourse.common.aop;

import com.google.common.base.Joiner;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Map;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Around("within(com.finance.bitcourse..*)")
    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
        String params = getRequestParams();

        log.info("----------> REQUEST : {}({}) = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), params);

        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable t) {
            log.error("----------> ERROR : {}({}), Error message: {}",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), t.getMessage(), t);
            throw t;
        }

        log.info("----------> RESPONSE : {}({}) = {})", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), result);

        return result;
    }


    private String getRequestParams() {

        String params = "";

        RequestAttributes requestAttribute = RequestContextHolder.getRequestAttributes();

        if(requestAttribute != null){
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes()).getRequest();

            Map<String, String[]> paramMap = request.getParameterMap();

            if(!paramMap.isEmpty()) {
                params = " [" + paramMapToString(paramMap) + "]";
            }
        }
        return params;
    }

    private String paramMapToString(Map<String, String[]> paramMap) {
        return paramMap.entrySet().stream()
                .map(entry -> String.format("%s -> (%s)",
                        entry.getKey(), Joiner.on(",").join(entry.getValue())))
                .collect(Collectors.joining(", "));
    }

}
