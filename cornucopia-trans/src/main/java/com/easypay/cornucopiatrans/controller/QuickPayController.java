package com.easypay.cornucopiatrans.controller;

import com.alibaba.fastjson.JSONObject;
import com.easypay.cornucopiacommon.enums.RespCode;
import com.easypay.cornucopiacommon.result.CommonResult;
import com.easypay.cornucopiatrans.biz.CheckUserStateBiz;
import com.easypay.cornucopiatrans.services.impl.QuickPayServiceImpl;
import com.easypay.cornucopiatrans.vo.BaseResponse;
import com.easypay.cornucopiatrans.vo.request.VoQuickPay;
import com.easypay.cornucopiatrans.vo.response.ResultQuickPay;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController()
public class QuickPayController {

    @Autowired
    private QuickPayServiceImpl cashService;


    @Autowired
    private CheckUserStateBiz checkUserStateBiz;

    @RequestMapping("/quickpay")
    public CommonResult bindSettleCard(VoQuickPay voQuickPay){
        ResultQuickPay resultQuickPay = new ResultQuickPay();

        BaseResponse baseResponse = checkUserStateBiz.checkUser(voQuickPay.getUserId());
        if (!RespCode.CODE_000.getRespCode().equals(baseResponse.getRespCode())){
            CommonResult.success(JSONObject.toJSONString(baseResponse));
        }

        baseResponse = cashService.doquickpay(voQuickPay);
        return CommonResult.success(JSONObject.toJSONString(resultQuickPay));
    }
}
