package com.easypay.cornucopiaquery.interceptor;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class RateLimiterIntercoptor  implements HandlerInterceptor {

    //每秒只发出20个令牌
    RateLimiter rateLimiter = RateLimiter.create(20);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!tryAcquire()){
            log.warn("并发量太大");
            throw new RuntimeException("系统繁忙，请稍后再试");
        }
        return true;
    }

    public boolean tryAcquire(){
        return rateLimiter.tryAcquire();
    }
}
