package com.easypay.cornucopiatrans.controller;

import com.alibaba.fastjson.JSONObject;
import com.easypay.cornucopiacommon.enums.RespCode;
import com.easypay.cornucopiacommon.result.CommonResult;
import com.easypay.cornucopiatrans.services.impl.RegisterServiceImpl;
import com.easypay.cornucopiatrans.vo.request.VoLogin;
import com.easypay.cornucopiatrans.vo.request.VoRegister;
import com.easypay.cornucopiatrans.vo.response.ResultLogin;
import com.easypay.cornucopiatrans.vo.response.ResultRegister;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("/trans")
public class RegisterController {

    @Autowired
    private RegisterServiceImpl registerService;

    @RequestMapping("/wechat/register")
    public CommonResult register(VoRegister voRegister){
        ResultRegister resultRegister = registerService.register( voRegister,"1");
        if (resultRegister == null){
            resultRegister.setRespCode(RespCode.CODE_002.getRespCode());
            resultRegister.setRespDesc(RespCode.CODE_002.getRespCode());
            return CommonResult.failed(JSONObject.toJSONString(resultRegister));
        }
        return CommonResult.success(JSONObject.toJSONString(resultRegister));
    }
}
