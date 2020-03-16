package com.easypay.cornucopiatrans.controller;

import com.alibaba.fastjson.JSONObject;
import com.easypay.cornucopiacommon.enums.RespCode;
import com.easypay.cornucopiacommon.result.CommonResult;
import com.easypay.cornucopiatrans.biz.CheckUserStateBiz;
import com.easypay.cornucopiatrans.services.impl.BindQuickCardServiceImpl;
import com.easypay.cornucopiatrans.services.impl.QueryAccoutInfoServiceImpl;
import com.easypay.cornucopiatrans.vo.BaseResponse;
import com.easypay.cornucopiatrans.vo.request.VoComm;
import com.easypay.cornucopiatrans.vo.request.VoQuickCard;
import com.easypay.cornucopiatrans.vo.response.ResultQueryAcctInfo;
import com.easypay.cornucopiatrans.vo.response.ResultQuickCard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class QueryAcctInfoController {

    @Autowired
    private QueryAccoutInfoServiceImpl queryAccoutInfoService;

    @Autowired
    private CheckUserStateBiz checkUserStateBiz;

    @RequestMapping("/queryAccoutInfo")
    public CommonResult bindQuickCard(VoComm voComm) {
        ResultQueryAcctInfo resultQueryAcctInfo = new ResultQueryAcctInfo();

        BaseResponse baseResponse = checkUserStateBiz.checkUser(voComm.getUserId());
        if (!RespCode.CODE_000.getRespCode().equals(baseResponse.getRespCode())){
            CommonResult.success(JSONObject.toJSONString(baseResponse));
        }

        resultQueryAcctInfo=queryAccoutInfoService.qyeryAcct(voComm.getUserId());

        return CommonResult.success(JSONObject.toJSONString(resultQueryAcctInfo));
    }
}
