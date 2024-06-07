package com.diary.bace.handler;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * 统一日志处理切面
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    private static final String LOCALHOST1 = "127.0.0.1";
    private static final String LOCALHOST2 = "0:0:0:0:0:0:0:1";

    //定义切点表达式,指定通知功能被应用的范围
    @Pointcut("execution(public * com.diary.controller.*.*(..))")
    public void webLog() {
        log.info("");
    }

    /**
     * 在切点之前织入
     *
     * @param joinPoint
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(null!=attributes) {
            HttpServletRequest request = attributes.getRequest();
            // 打印请求相关参数
            log.info("========================================== Start ==========================================");
            // 打印请求 url
            log.info("URL            : {}", request.getRequestURL().toString());
            // 打印 Http method
            log.info("HTTP Method    : {}", request.getMethod());
            // 打印调用 controller 的全路径以及执行方法
            log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            // 打印请求的 IP
            log.info("IP             : {}", getIpAddr(request));
            // 打印请求入参
            printRequestParamsLog(joinPoint);
        }
    }

    /**
     * 在切点之后织入
     *
     * @throws Throwable
     */
    @After("webLog()")
    public void doAfter() {
        log.info("=========================================== End ===========================================");
        // 每个请求之间空一行
        log.info("");
    }

    /**
     * 环绕
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        // 打印出参
        log.info("Response Result: {}", JSON.toJSONString(result));
        // 执行耗时
        log.info("Use Time       : {} ms", System.currentTimeMillis() - startTime);
        return result;
    }


    /**
     * 打印request请求参数
     */
    public void printRequestParamsLog(JoinPoint joinPoint) {
        String[] parameterNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        Object[] parameterValues = joinPoint.getArgs(); // 获取方法参数
        for (int i = 0; i < parameterValues.length; i++) {
            log.info("Request params : {}", parameterNames[i] + " = " + parameterValues[i]);
        }
    }

    /**
     * 打印header信息
     */
    public void printHeaderInfoLog(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            log.info("Header         : {}", key + " = " + value);
        }
    }

    /**
     * @Description: 获取ip
     */
    private String getIpAddr(HttpServletRequest request) {
        String unKnown = "unknown";
        String ipAddress = null;
        ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || unKnown.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || unKnown.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || unKnown.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (LOCALHOST1.equals(ipAddress) || LOCALHOST2.equals(ipAddress)) {
                try {
                    InetAddress inet =  InetAddress.getLocalHost();
                    ipAddress = inet.getHostAddress();
                } catch (UnknownHostException e) {
                    log.info(e.getMessage());
                }
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15 && ipAddress.contains(",")) { // "***.***.***.***".length()
            ipAddress = ipAddress.substring(0, ipAddress.indexOf(','));
        }
        // 或者这样也行,对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        return ipAddress;
    }
}