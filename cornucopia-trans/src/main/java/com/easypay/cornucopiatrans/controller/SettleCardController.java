package com.easypay.cornucopiatrans.controller;

import com.alibaba.fastjson.JSONObject;
import com.easypay.cornucopiacommon.enums.RespCode;
import com.easypay.cornucopiacommon.result.CommonResult;
import com.easypay.cornucopiatrans.biz.CheckUserStateBiz;
import com.easypay.cornucopiatrans.services.impl.BindSettleCardServiceImpl;
import com.easypay.cornucopiatrans.vo.BaseResponse;
import com.easypay.cornucopiatrans.vo.request.VoSettleCard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SettleCardController {

    @Autowired
    private BindSettleCardServiceImpl bindSettleCard;

    @Autowired
    private CheckUserStateBiz checkUserStateBiz;

    @RequestMapping("/bindSettleCard")
    public CommonResult bindSettleCard(VoSettleCard voSettleCard){
        BaseResponse baseResponse = new BaseResponse();

        baseResponse = checkUserStateBiz.checkUser(voSettleCard.getUserId());
        if (!RespCode.CODE_000.getRespCode().equals(baseResponse.getRespCode())){
            CommonResult.success(JSONObject.toJSONString(baseResponse));
        }

        baseResponse = bindSettleCard.bindSettleCard(voSettleCard.getUserId(),voSettleCard.getCardNo(),voSettleCard.getCardDistrict());


        return CommonResult.success(JSONObject.toJSONString(baseResponse));
    }
}
