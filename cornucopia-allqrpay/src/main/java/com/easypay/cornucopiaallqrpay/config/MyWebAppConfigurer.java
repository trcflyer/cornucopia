package com.easypay.cornucopiaallqrpay.config;

import org.springframework.boot.web.servlet.filter.OrderedCharacterEncodingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class MyWebAppConfigurer
        extends WebMvcConfigurationSupport {


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
}