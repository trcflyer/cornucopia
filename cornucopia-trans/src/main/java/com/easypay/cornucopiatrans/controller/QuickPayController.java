package com.easypay.cornucopiatrans.controller;

import com.alibaba.fastjson.JSONObject;
import com.easypay.cornucopiacommon.result.CommonResult;
import com.easypay.cornucopiatrans.services.impl.QuickPayServiceImpl;
import com.easypay.cornucopiatrans.vo.BaseResponse;
import com.easypay.cornucopiatrans.vo.request.VoQuickPay;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("/trans")
public class QuickPayController {

    @Autowired
    private QuickPayServiceImpl cashService;

    @RequestMapping("/quickpay")
    public CommonResult bindSettleCard(VoQuickPay voQuickPay){
        BaseResponse baseResponse = new BaseResponse();

        baseResponse = cashService.doquickpay(voQuickPay);
        return CommonResult.success(JSONObject.toJSONString(baseResponse));
    }
}
