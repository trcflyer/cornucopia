package com.easypay.cornucopiatrans.controller;

import com.alibaba.fastjson.JSONObject;
import com.easypay.cornucopiacommon.result.CommonResult;
import com.easypay.cornucopiatrans.services.impl.BindSettleCardServiceImpl;
import com.easypay.cornucopiatrans.vo.BaseResponse;
import com.easypay.cornucopiatrans.vo.request.VoSettleCard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("/trans")
public class SettleCardController {

    @Autowired
    private BindSettleCardServiceImpl bindSettleCard;

    @RequestMapping("/bindSettleCard")
    public CommonResult bindSettleCard(VoSettleCard voSettleCard){
        BaseResponse baseResponse = new BaseResponse();

        baseResponse = bindSettleCard.bindSettleCard(voSettleCard.getUserId(),voSettleCard.getBankId(),voSettleCard.getCardNo());


        return CommonResult.success(JSONObject.toJSONString(baseResponse));
    }
}
