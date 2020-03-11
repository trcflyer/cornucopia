package com.easypay.cornucopiatrans.controller;

import com.alibaba.fastjson.JSONObject;
import com.easypay.cornucopiacommon.result.CommonResult;
import com.easypay.cornucopiatrans.services.impl.CheckRealServiceImpl;
import com.easypay.cornucopiatrans.vo.BaseResponse;
import com.easypay.cornucopiatrans.vo.request.VoCheckName;
import com.easypay.cornucopiatrans.vo.request.VoCheckPhoto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("trans")
public class AuthController {

    @Autowired
    private CheckRealServiceImpl checkRealService;

    @RequestMapping("/check/name")
    public CommonResult realname(VoCheckName voCheckName){
        BaseResponse baseResponse = new BaseResponse();

        baseResponse = checkRealService.checkName(voCheckName.getUserId(),voCheckName.getIdNo(),voCheckName.getIdNo());

        return CommonResult.success(JSONObject.toJSONString(baseResponse));
    }
    @RequestMapping("/check/photo")
    public CommonResult photo(VoCheckPhoto voCheckPhoto){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse = checkRealService.checkPhoto(voCheckPhoto.getUserId(),voCheckPhoto.getPhotoF(),voCheckPhoto.getPhotoB());

        return CommonResult.success(JSONObject.toJSONString(baseResponse));
    }

}
