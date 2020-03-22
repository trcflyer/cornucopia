package com.easypay.cornucopiaallqrpay.controller;

import com.alibaba.fastjson.JSONObject;
import com.easypay.cornucopiaallqrpay.service.IMchInfoService;
import com.easypay.cornucopiaallqrpay.service.IPayOrderService;
import com.easypay.cornucopiacommon.constant.PayConstant;
import com.easypay.cornucopiacommon.utils.XXPayUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


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
public class QueryPayOrderController {

    @Autowired
    private IPayOrderService payOrderService;

    @Autowired
    private IMchInfoService mchInfoService;

    /**
     * 查询支付订单接口:
     * 1)先验证接口参数以及签名信息
     * 2)根据参数查询订单
     * 3)返回订单数据
     * @param params
     * @return
     */
    @RequestMapping(value = "/api/pay/query_order")
    public String queryPayOrder(@RequestParam String params) {
    	JSONObject po = JSONObject.parseObject(params);
    	return queryPayOrder(po);
    }
    
    @RequestMapping(value = "/api/pay/query_order", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String queryPayOrder(@RequestBody JSONObject params) {
        log.info("###### 开始接收商户查询支付订单请求 ######");
        String logPrefix = "【商户支付订单查询】";
        try {
            JSONObject payContext = new JSONObject();
            // 验证参数有效性
            String errorMessage = validateParams(params, payContext);
            if (!"success".equalsIgnoreCase(errorMessage)) {
                log.warn(errorMessage);
                return XXPayUtil.makeRetFail(XXPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, errorMessage, null, null));
            }
            log.debug("请求参数及签名校验通过");
            String mchId = params.getString("mchId"); 			    // 商户ID
            String mchOrderNo = params.getString("mchOrderNo"); 	// 商户订单号
            String payOrderId = params.getString("payOrderId"); 	// 支付订单号
            String executeNotify = params.getString("executeNotify");   // 是否执行回调
            JSONObject payOrder = payOrderService.queryPayOrder(mchId, payOrderId, mchOrderNo, executeNotify);
            log.info("{}查询支付订单,结果:{}", logPrefix, payOrder);
            if (payOrder == null) {
                return XXPayUtil.makeRetFail(XXPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "支付订单不存在", null, null));
            }
            Map<String, Object> map = XXPayUtil.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "", PayConstant.RETURN_VALUE_SUCCESS, null);
            map.put("result", payOrder);
            log.info("###### 商户查询订单处理完成 ######");
            return XXPayUtil.makeRetData(map, payContext.getString("resKey"));
        }catch (Exception e) {
            log.error(e.getMessage(), "");
            return XXPayUtil.makeRetFail(XXPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "支付中心系统异常", null, null));
        }
    }

    /**
     * 验证创建订单请求参数,参数通过返回JSONObject对象,否则返回错误文本信息
     * @param params
     * @return
     */
    private String validateParams(JSONObject params, JSONObject payContext) {
        // 验证请求参数,参数有问题返回错误提示
        String errorMessage;
        // 支付参数
        String mchId = params.getString("mchId"); 			    // 商户ID
        String mchOrderNo = params.getString("mchOrderNo"); 	// 商户订单号
        String payOrderId = params.getString("payOrderId"); 	// 支付订单号

        String sign = params.getString("sign"); 				// 签名

        // 验证请求参数有效性（必选项）
        if(StringUtils.isBlank(mchId)) {
            errorMessage = "request params[mchId] error.";
            return errorMessage;
        }
        if(StringUtils.isBlank(mchOrderNo) && StringUtils.isBlank(payOrderId)) {
            errorMessage = "request params[mchOrderNo or payOrderId] error.";
            return errorMessage;
        }

        // 签名信息
        if (StringUtils.isEmpty(sign)) {
            errorMessage = "request params[sign] error.";
            return errorMessage;
        }

        // 查询商户信息
        JSONObject mchInfo = mchInfoService.getByMchId(mchId);
        if(mchInfo == null) {
            errorMessage = "Can't found mchInfo[mchId="+mchId+"] record in db.";
            return errorMessage;
        }
        if(mchInfo.getByte("state") != 1) {
            errorMessage = "mchInfo not available [mchId="+mchId+"] record in db.";
            return errorMessage;
        }

        String reqKey = mchInfo.getString("reqKey");
        if (StringUtils.isBlank(reqKey)) {
            errorMessage = "reqKey is null[mchId="+mchId+"] record in db.";
            return errorMessage;
        }
        payContext.put("resKey", mchInfo.getString("resKey"));

        // 验证签名数据
        boolean verifyFlag = XXPayUtil.verifyPaySign(params, reqKey);
        if(!verifyFlag) {
            errorMessage = "Verify XX pay sign failed.";
            return errorMessage;
        }

        return "success";
    }

}
