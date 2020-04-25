package com.easypay.cornucopiaallqrpay.controller;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.easypay.cornucopiaallqrpay.DefaultRequestValue;
import com.easypay.cornucopiaallqrpay.biz.CheckMerIdBiz;
import com.easypay.cornucopiaallqrpay.dal.dao.impl.TMchInfoMapperImpl;
import com.easypay.cornucopiaallqrpay.dal.pojo.TMchInfo;
import com.easypay.cornucopiaallqrpay.vo.response.CreateOrderResponse;
import com.easypay.cornucopiacommon.constant.PayConstant;
import com.easypay.cornucopiacommon.result.CommonResult;
import com.easypay.cornucopiacommon.result.ResultCode;
import com.easypay.cornucopiacommon.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 反扫刷脸支付接口
 */
@Slf4j
@RestController
@RequestMapping("/sweep")
public class ReverseSweepOrderController {
    @Autowired
    private TMchInfoMapperImpl tMchInfoMapper;

    @Autowired
    private DefaultRequestValue defaultRequestValue;

    @Value("${frontrsa.publickey}")
    String publickey;

    @Value("${frontrsa.privatekey}")
    String privatekey;

    @Value("${myrsa.publickey}")
    String mypublickey;

    @Value("${myrsa.privatekey}")
    String myprivatekey;

    @Autowired
    private CheckMerIdBiz checkMerIdBiz;

    private Map createPayOrder(String ordAmt ,Map<String, String> params, String mchId) {
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("mchId", mchId);                       // 商户ID
        paramMap.put("channelId", params.get("channelId"));             // 支付渠道ID, WX_NATIVE,ALIPAY_WAP
        paramMap.put("amount", ordAmt);                          // 支付金额,单位分
        paramMap.put("currency", "cny");                    // 币种, cny-人民币
        paramMap.put("clientIp", "114.112.124.236");        // 用户地址,IP或手机号
        paramMap.put("device", "WEB");                      // 设备
        paramMap.put("subject", "反扫刷脸支付");
        paramMap.put("body", "反扫刷脸支付");
        paramMap.put("param1", params.get("scene"));                         // 扩展参数1
        paramMap.put("param2", params.get("authCode"));                         // 扩展参数2

        try {
            String reqSign = EncryptUtil.encrypt(RSAEncryptUtil.sign(paramMap, myprivatekey)).getCipherText();
            paramMap.put("sign", reqSign);   // 签名
        } catch (Exception e) {
            log.info("签名异常", e.getMessage());
            return null;
        }

        JSONObject requestJSONObject = new JSONObject();
        requestJSONObject.put("params", JSONObject.toJSONString(paramMap));
        log.info("请求支付中心下单接口,请求数据:" + JSONObject.toJSONString(paramMap));
        String url = defaultRequestValue.getBaseUrl() + "/api/pay/create_order?";
        String result = HttpUtil.post(url, JSONObject.toJSONString(paramMap));

        log.info("请求支付中心下单接口,响应数据:" + result);
        Map retMap = JSON.parseObject(result);
        if ("SUCCESS".equals(retMap.get("retCode"))) {
           log.info("=========支付中心下单成功=========");
        }else{
            log.info("=========支付中心下单失败=========");
            return null;
        }
        return retMap;
    }

    @RequestMapping("/qrPay")
    public CommonResult qrPay( HttpServletRequest request) {
        CreateOrderResponse response = new CreateOrderResponse();
        String logPrefix = "【条码支付】";
        String scene = request.getParameter("scene");
        String authCode = request.getParameter("authCode");
        String payAmt = request.getParameter("payAmt");
        String memberId  = request.getParameter("memberId");
        String ordAmt = AmountUtil.convertDollar2Cent(payAmt);

        if (StringUtils.isBlank(memberId) || StringUtils.isBlank(payAmt) || StringUtils.isBlank(scene) || StringUtils.isBlank(authCode)) {
            log.info("{}请求参数错误,{}", logPrefix,"必填参数不可为空");
            return CommonResult.failed(ResultCode.CODE_999,"必填参数不可为空");
        }

       TMchInfo tMchInfo =  tMchInfoMapper.selectByMemberId(memberId);
       if(tMchInfo == null){
           log.info("{}请求参数错误,{}", logPrefix,"商户号不存在");
           return CommonResult.failed(ResultCode.CODE_999,"商户号不存在");
       }

        // 先插入订单数据
        Map<String, String> orderMap = null;
        Map<String, String> params = new  HashMap<>();
        params.put("channelId",PayConstant.PAY_CHANNEL_ALIPAY_BAR_CODE);
        params.put("scene",scene);
        params.put("authCode",authCode);

        orderMap = createPayOrder(ordAmt, params, tMchInfo.getMchId());
        if(orderMap == null){
            return CommonResult.failed(ResultCode.CODE_200);
        }
        response.setPayOrderId(orderMap.get("payOrderId"));
        response.setOrderInfo(orderMap.get("payUrl"));
        return CommonResult.success(ResultCode.SUCCESS,JSONObject.toJSONString(response));
    }

}