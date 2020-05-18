package com.easypay.cornucopiaallqrpay.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.easypay.cornucopiaallqrpay.biz.CheckMerIdBiz;
import com.easypay.cornucopiaallqrpay.service.IPayOrderService;
import com.easypay.cornucopiaallqrpay.util.MyChannelUtil;
import com.easypay.cornucopiacommon.constant.PayConstant;
import com.easypay.cornucopiacommon.result.CommonResult;
import com.easypay.cornucopiacommon.result.ResultCode;
import com.easypay.cornucopiacommon.utils.AmountUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 支付订单查询
 * @author dingzhiwei jmdhappy@126.com
 * @date 2017-08-31
 * @version V1.0
 * @Copyright: www.xxpay.org
 */
@RestController
@Slf4j
@RequestMapping("/query")
public class QueryPayOrderController {

    @Autowired
    private CheckMerIdBiz checkMerIdBiz;

    @Autowired
    private IPayOrderService payOrderService;


    /**
     * 查询支付订单接口:
     * 1)先验证接口参数以及签名信息
     * 2)根据参数查询订单
     * 3)返回订单数据
     * @param request
     * @param response
     * @return
     */

    @RequestMapping(value = "/pay/orderDetail")
    public CommonResult queryPayOrder(HttpServletRequest request, HttpServletResponse response) {
        log.info("###### 开始接收商户查询支付订单请求 ######");
        String logPrefix = "【商户支付订单查询】";
        try {
            JSONObject payContext = new JSONObject();
            // 验证参数有效性
            String errorMessage = validateParams(request, payContext);
            if (!"success".equalsIgnoreCase(errorMessage)) {
                log.warn(errorMessage);
                return CommonResult.failed(ResultCode.CODE_999,"必填参数不可为空");
            }
            log.debug("请求参数及签名校验通过");
            String mchId = request.getParameter("mchId"); 			    // 商户ID
            String mchOrderId = request.getParameter("mchOrderId"); 	// 商户订单号
            String payOrderId = request.getParameter("payOrderId"); 	// 支付订单号
            String deviceSn = request.getParameter("deviceSn"); 	// 支付订单号
            String executeNotify = request.getParameter("executeNotify");   // 是否执行回调
            JSONObject payOrder = payOrderService.queryPayOrder(mchId, deviceSn,payOrderId, mchOrderId, executeNotify);
            log.info("{}查询支付订单,结果:{}", logPrefix, payOrder);
            if (payOrder == null) {
                return CommonResult.failed(ResultCode.CODE_999,"支付订单不存在");
            }
            log.info("###### 商户查询订单处理完成 ######");
            Map<String,String> mapResult = new HashMap<>();
            mapResult.put("mchId",mchId);
            mapResult.put("mchOrderId",payOrder.getString("mchOrderId"));
            mapResult.put("payOrderId",payOrder.getString("payOrderId"));
            mapResult.put("ordAmt",AmountUtil.convertCent2Dollar(String.valueOf(payOrder.get("amount"))));
            mapResult.put("ordStatus",payOrder.getString("status"));
            mapResult.put("channelId", MyChannelUtil.getChannelName(payOrder.getString("channelId")));
            return CommonResult.success(JSONObject.toJSONString(mapResult));
        }catch (Exception e) {
            log.error(e.getMessage(), "");
            return CommonResult.failed(ResultCode.CODE_998,"支付系统异常");
        }
    }

    /**
     * 验证创建订单请求参数,参数通过返回JSONObject对象,否则返回错误文本信息
     * @param request
     * @return
     */
    private String validateParams(HttpServletRequest request, JSONObject payContext) {
        // 验证请求参数,参数有问题返回错误提示
        String errorMessage;
        // 支付参数
        String mchId = request.getParameter("mchId"); 			    // 商户ID
        String mchOrderId = request.getParameter("mchOrderId"); 	// 商户订单号
        String payOrderId = request.getParameter("payOrderId"); 	// 支付订单号
        String deviceSn = request.getParameter("deviceSn"); 	// 支付订单号

        // 验证请求参数有效性（必选项）
        if(StringUtils.isBlank(mchId)) {
            errorMessage = "request params[mchId] error.";
            return errorMessage;
        }
        if(StringUtils.isBlank(deviceSn)) {
            errorMessage = "request params[deviceSn] error.";
            return errorMessage;
        }
        if(StringUtils.isBlank(mchOrderId) && StringUtils.isBlank(payOrderId)) {
            errorMessage = "request params[mchOrderId or payOrderId] error.";
            return errorMessage;
        }

        // 查询商户信息
        ResultCode respCode = checkMerIdBiz.checkMer(mchId);
        if (!ResultCode.SUCCESS.equals(respCode)) {
            errorMessage = "商户号错误";
            return errorMessage;
        }

        return "success";
    }


    /**
     * 查询支付订单接口:
     * 1)先验证接口参数以及签名信息
     * 2)根据参数查询订单
     * 3)返回订单数据
     * @param request
     * @param response
     * @return
     */

    @RequestMapping(value = "/pay/orderList")
    public CommonResult queryPayOrderList(HttpServletRequest request, HttpServletResponse response) {
        log.info("###### 开始接收商户查询支付订单列表请求 ######");
        String logPrefix = "【商户支付订单列表查询】";
        try {
            JSONObject payContext = new JSONObject();
            // 验证参数有效性
            String errorMessage = validateParamsList(request, payContext);
            if (!"success".equalsIgnoreCase(errorMessage)) {
                log.warn(errorMessage);
                return CommonResult.failed(ResultCode.CODE_999,"必填参数不可为空");
            }
            log.debug("请求参数及签名校验通过");
            String mchId = request.getParameter("mchId"); 			    // 商户ID
            String deviceSn = request.getParameter("deviceSn"); 	// 支付订单号
            String pageNum = request.getParameter("pageNum");
            String pageSize = request.getParameter("pageSize");
            String transDate = request.getParameter("transDate");
            String scene = request.getParameter("scene");

            JSONObject orderInfo = payOrderService.queryPayOrderList(mchId, deviceSn,pageNum,pageSize,transDate,scene);
            log.info("{}查询支付订单,结果:{}", logPrefix, orderInfo);
            if (orderInfo == null) {
                return CommonResult.failed(ResultCode.CODE_999,"支付订单列表为空");
            }
            Map<String,String> mapResult = new HashMap<>();
            mapResult.put("mchId",mchId);
            mapResult.put("orderInfo",orderInfo.toJSONString());
            return CommonResult.success(JSONObject.toJSONString(mapResult));
        }catch (Exception e) {
            log.error(e.getMessage(), "");
            return CommonResult.failed(ResultCode.CODE_998,"支付系统异常");
        }
    }

    /**
     * 验证创建订单请求参数,参数通过返回JSONObject对象,否则返回错误文本信息
     * @param request
     * @return
     */
    private String validateParamsList(HttpServletRequest request, JSONObject payContext) {
        // 验证请求参数,参数有问题返回错误提示
        String errorMessage;
        // 支付参数
        String mchId = request.getParameter("mchId"); 			    // 商户ID
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        String transDate = request.getParameter("transDate");


        // 验证请求参数有效性（必选项）
        if(StringUtils.isBlank(mchId)) {
            errorMessage = "request params[mchId] error.";
            return errorMessage;
        }
        if(StringUtils.isBlank(transDate)) {
            errorMessage = "request params[transDate] error.";
            return errorMessage;
        }
        if(StringUtils.isBlank(pageNum)||StringUtils.isBlank(pageSize)) {
            return "分页查询参数不可为空";
        }

        // 查询商户信息
        ResultCode respCode = checkMerIdBiz.checkMer(mchId);
        if (!ResultCode.SUCCESS.equals(respCode)) {
            errorMessage = "商户号错误";
            return errorMessage;
        }
        return "success";
    }
}
