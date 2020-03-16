package com.easypay.cornucopiacommon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.easypay")
@SpringBootApplication
public class CornucopiaCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(CornucopiaCommonApplication.class, args);
    }

}
