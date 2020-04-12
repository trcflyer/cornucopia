package com.easypay.cornucopiaallqrpay.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MonitorController extends BaseController{


    @RequestMapping("monitorWeb")
    public String test(){

        return "WebOk";
    }
}
