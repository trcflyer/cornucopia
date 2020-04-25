package com.easypay.cornucopiaallqrpay.controller;

import com.alibaba.fastjson.JSONObject;
import com.easypay.cornucopiacommon.exception.BizException;
import com.easypay.cornucopiacommon.result.CommonResult;
import com.easypay.cornucopiaallqrpay.vo.BaseResponse;
import com.easypay.cornucopiacommon.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@ControllerAdvice
public class BaseController {


    @ExceptionHandler(BizException.class)
    @ResponseBody
    public CommonResult exception(HttpServletRequest request, HttpServletResponse response,Exception e){
        log.warn(e.getMessage());
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setRespCode(String.valueOf(ResultCode.CODE_998.getCode()));
        baseResponse.setRespDesc(e.getMessage());
        return CommonResult.success(JSONObject.toJSONString(baseResponse));
    }

    /**
     * validation异常捕获处理 （可能是MethodArgumentNotValidException或 BindException）
     * @param e  MethodArgumentNotValidException 异常
     * @return com.example.common.CreatePayOrder.JsonResult
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public CommonResult serviceHandle(BindException e) {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setRespCode(String.valueOf(ResultCode.CODE_999.getCode()));
        baseResponse.setRespDesc(allErrors.get(0).getDefaultMessage());
        return CommonResult.success(JSONObject.toJSONString(baseResponse));
    }
}
