package com.easypay.cornucopiatrans.controller;

import com.alibaba.fastjson.JSONObject;
import com.easypay.cornucopiacommon.result.CommonResult;
import com.easypay.cornucopiatrans.services.impl.BindQuickCardServiceImpl;
import com.easypay.cornucopiatrans.vo.BaseResponse;
import com.easypay.cornucopiatrans.vo.request.VoQuickCard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("/trans")
public class QuickCardController {

    @Autowired
    private BindQuickCardServiceImpl bindQuickCardService;

    @RequestMapping("/bindQuickCard")
    public CommonResult bindQuickCard(VoQuickCard voQuickCard){
        BaseResponse baseResponse = new BaseResponse();

        baseResponse = bindQuickCardService.bindQuickCard(voQuickCard.getUserId(),voQuickCard.getBankId(),voQuickCard.getCardNo());


        return CommonResult.success(JSONObject.toJSONString(baseResponse));
    }
}
