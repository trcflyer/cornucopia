package com.easypay.cornucopiaallqrpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan("com.easypay")
@SpringBootApplication
@ServletComponentScan
public class CornucopiaAllqrpayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CornucopiaAllqrpayApplication.class, args);
    }

}
