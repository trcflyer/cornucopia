package com.easypay.cornucopiaquery.controller;

import com.easypay.cornucopiacommon.result.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@ControllerAdvice(basePackages="com.easypay.cornucopiaquery.controller")
public class BaseController {


    @ExceptionHandler(RuntimeException.class)
    public CommonResult<String> exception(HttpServletRequest request, HttpServletResponse response,Exception e){
        log.warn(e.getMessage());
       return CommonResult.failed(e.getMessage());
    }
}
