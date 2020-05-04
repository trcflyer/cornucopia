package com.easypay.cornucopiaallqrpay.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
public class LogInterceptor implements HandlerInterceptor {
    private final static String TRACEID = "traceId";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MDC.put(TRACEID, UUID.randomUUID().toString().replace("-",""));
        getRequestIp(request);
        Map<String, String> requestMap = getAllRequestParam(request);
        log.info("请求地址为:{},请求参数为:{}",request.getRequestURI(),requestMap);
        return true;
    }

    private Map<String, String> getAllRequestParam(final HttpServletRequest request) {
        Map<String, String> res = new HashMap<String, String>();
        Enumeration<?> temp = request.getParameterNames();
        if (null != temp) {
            while (temp.hasMoreElements()) {
                String en = (String) temp.nextElement();
                String value = request.getParameter(en);
                res.put(en, value);
            }
        }
        return res;
    }
    private void getRequestIp(HttpServletRequest request){
        // 获取请求IP地址
        String ipAddr = request.getHeader("X-Forwarded-For");
        if (ipAddr == null || ipAddr.trim().length() == 0) {
            ipAddr = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddr == null || ipAddr.trim().length() == 0) {
            ipAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddr == null || ipAddr.trim().length() == 0) {
            ipAddr = request.getRemoteAddr();
        }
        request.setAttribute("clientIp", ipAddr);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.remove(TRACEID);
    }
}
