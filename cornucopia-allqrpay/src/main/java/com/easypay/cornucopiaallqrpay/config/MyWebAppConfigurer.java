package com.easypay.cornucopiaallqrpay.config;

import com.easypay.cornucopiaallqrpay.interceptor.LogInterceptor;
import com.easypay.cornucopiaallqrpay.interceptor.RateLimiterIntercoptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.filter.OrderedCharacterEncodingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.Charset;

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
        //重写这个方法，映射静态资源文件
        @Override
        protected void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
            super.addResourceHandlers(registry);
        }
    @Bean
    public CharacterEncodingFilter characterEncodingFilter(){
        CharacterEncodingFilter filter = new OrderedCharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return converter;
    }
}