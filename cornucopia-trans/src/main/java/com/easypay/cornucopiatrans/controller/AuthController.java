package com.easypay.cornucopiatrans.controller;

import com.alibaba.fastjson.JSONObject;
import com.easypay.cornucopiacommon.enums.RespCode;
import com.easypay.cornucopiacommon.result.CommonResult;
import com.easypay.cornucopiatrans.biz.CheckUserStateBiz;
import com.easypay.cornucopiatrans.services.impl.CheckRealServiceImpl;
import com.easypay.cornucopiatrans.vo.BaseResponse;
import com.easypay.cornucopiatrans.vo.request.VoCheckName;
import com.easypay.cornucopiatrans.vo.request.VoCheckPhoto;
import com.sun.org.apache.regexp.internal.RE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController()
public class AuthController {

    @Autowired
    private CheckRealServiceImpl checkRealService;

    @Autowired
    private CheckUserStateBiz checkUserStateBiz;

    @RequestMapping("/check/name")
    @ResponseBody
    public CommonResult realname(@Validated  VoCheckName voCheckName){
        BaseResponse baseResponse = new BaseResponse();

        baseResponse = checkUserStateBiz.checkUser(voCheckName.getUserId());
        if (!RespCode.CODE_000.getRespCode().equals(baseResponse.getRespCode())){
            CommonResult.success(JSONObject.toJSONString(baseResponse));
        }

        baseResponse = checkRealService.checkName(voCheckName.getUserId(),voCheckName.getIdNo(),voCheckName.getLegalName(),voCheckName.getUserName());

        return CommonResult.success(JSONObject.toJSONString(baseResponse));
    }
    @RequestMapping("/check/photo")
    public CommonResult photo(VoCheckPhoto voCheckPhoto){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse = checkRealService.checkPhoto(voCheckPhoto.getUserId(),voCheckPhoto.getPhotoF(),voCheckPhoto.getPhotoB());

        return CommonResult.success(JSONObject.toJSONString(baseResponse));
    }

}
