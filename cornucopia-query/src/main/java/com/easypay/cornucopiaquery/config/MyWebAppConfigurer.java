package com.easypay.cornucopiaquery.config;

import com.easypay.cornucopiaquery.interceptor.LogInterceptor;
import com.easypay.cornucopiaquery.interceptor.RateLimiterIntercoptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.filter.OrderedCharacterEncodingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class MyWebAppConfigurer
        extends WebMvcConfigurationSupport {

    @Autowired
    private LogInterceptor logInterceptor;

    @Autowired
    private RateLimiterIntercoptor rateLimiterIntercoptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(logInterceptor).addPathPatterns("/**");
        registry.addInterceptor(rateLimiterIntercoptor).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    @Bean
    public CharacterEncodingFilter characterEncodingFilter(){
        CharacterEncodingFilter filter = new OrderedCharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }
}