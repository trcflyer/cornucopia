package com.easypay.cornucopiatrans;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.easypay")
@SpringBootApplication
@ServletComponentScan
public class CornucopiaTransApplication {

    public static void main(String[] args) {
        SpringApplication.run(CornucopiaTransApplication.class, args);
    }

}
