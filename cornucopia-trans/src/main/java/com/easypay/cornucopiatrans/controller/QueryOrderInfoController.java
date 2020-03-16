package com.easypay.cornucopiatrans.controller;

import com.alibaba.fastjson.JSONObject;
import com.easypay.cornucopiacommon.enums.RespCode;
import com.easypay.cornucopiacommon.result.CommonResult;
import com.easypay.cornucopiatrans.biz.CheckUserStateBiz;
import com.easypay.cornucopiatrans.services.impl.QueryAccoutInfoServiceImpl;
import com.easypay.cornucopiatrans.services.impl.QueryOrderInfoServuceImpl;
import com.easypay.cornucopiatrans.vo.BaseResponse;
import com.easypay.cornucopiatrans.vo.request.VoComm;
import com.easypay.cornucopiatrans.vo.response.ResultQueryAcctInfo;
import com.easypay.cornucopiatrans.vo.response.ResultQueryOrderLit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class QueryOrderInfoController {

    @Autowired
    private QueryOrderInfoServuceImpl queryOrderInfoServuce;

    @Autowired
    private CheckUserStateBiz checkUserStateBiz;

    @RequestMapping("/queryOrderLit")
    public CommonResult bindQuickCard(VoComm voComm) {
        ResultQueryOrderLit resultQueryOrderLit = new ResultQueryOrderLit();

        BaseResponse baseResponse = checkUserStateBiz.checkUser(voComm.getUserId());
        if (!RespCode.CODE_000.getRespCode().equals(baseResponse.getRespCode())){
            CommonResult.success(JSONObject.toJSONString(baseResponse));
        }

        resultQueryOrderLit=queryOrderInfoServuce.queryOrderLit(voComm.getUserId());

        return CommonResult.success(JSONObject.toJSONString(resultQueryOrderLit));
    }
}
