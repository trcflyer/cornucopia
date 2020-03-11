package com.easypay.cornucopiatrans.controller;

import com.alibaba.fastjson.JSONObject;
import com.easypay.cornucopiacommon.enums.RespCode;
import com.easypay.cornucopiacommon.result.CommonResult;
import com.easypay.cornucopiatrans.services.impl.LoginServiceImpl;
import com.easypay.cornucopiatrans.vo.request.VoLogin;
import com.easypay.cornucopiatrans.vo.response.ResultLogin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("trans")
public class LoginController {

    @Autowired
    private LoginServiceImpl loginService;

    @RequestMapping("/wechat/login")
    public CommonResult login(VoLogin voLogin){
        ResultLogin  resultLogin = loginService.login( voLogin);
        if (resultLogin == null){
            resultLogin.setRespCode(RespCode.CODE_001.getRespCode());
            resultLogin.setRespDesc(RespCode.CODE_001.getRespCode());
            return CommonResult.failed(JSONObject.toJSONString(resultLogin));
        }

        resultLogin.setRespCode(RespCode.CODE_000.getRespCode());
        resultLogin.setRespDesc(RespCode.CODE_000.getRespCode());
        return CommonResult.success(JSONObject.toJSONString(resultLogin));
    }
}
