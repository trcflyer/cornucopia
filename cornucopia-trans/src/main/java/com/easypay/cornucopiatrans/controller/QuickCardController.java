package com.easypay.cornucopiatrans.controller;

import com.alibaba.fastjson.JSONObject;
import com.easypay.cornucopiacommon.enums.RespCode;
import com.easypay.cornucopiacommon.result.CommonResult;
import com.easypay.cornucopiatrans.biz.CheckUserStateBiz;
import com.easypay.cornucopiatrans.services.impl.BindQuickCardServiceImpl;
import com.easypay.cornucopiatrans.vo.BaseResponse;
import com.easypay.cornucopiatrans.vo.request.VoQuickCard;
import com.easypay.cornucopiatrans.vo.response.ResultQuickCard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
public class QuickCardController {

    @Autowired
    private BindQuickCardServiceImpl bindQuickCardService;

    @Autowired
    private CheckUserStateBiz checkUserStateBiz;

    @RequestMapping("/bindQuickCard")
    public CommonResult bindQuickCard(VoQuickCard voQuickCard) {
        ResultQuickCard resultQuickCard = new ResultQuickCard();

        BaseResponse baseResponse = checkUserStateBiz.checkUser(voQuickCard.getUserId());
        if (!RespCode.CODE_000.getRespCode().equals(baseResponse.getRespCode())){
            CommonResult.success(JSONObject.toJSONString(baseResponse));
        }

        resultQuickCard = bindQuickCardService.bindQuickCard(voQuickCard.getUserId());

        return CommonResult.success(JSONObject.toJSONString(resultQuickCard));
    }
}
