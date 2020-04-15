package com.easypay.cornucopiaallqrpay.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@Controller
public class PayFinishController {

    /**
     * 微信支付(统一下单接口)后台通知响应
     * @param request
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/payFinish.htm")
    public String wxPayNotifyRes(HttpServletRequest request)  {
        return "payFinish";
    }
}
