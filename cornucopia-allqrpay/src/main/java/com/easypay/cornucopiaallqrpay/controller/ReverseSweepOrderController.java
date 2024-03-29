package com.easypay.cornucopiaallqrpay.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.easypay.cornucopiaallqrpay.DefaultRequestValue;
import com.easypay.cornucopiaallqrpay.biz.CheckMerIdBiz;
import com.easypay.cornucopiaallqrpay.biz.CheckMerOrdIdBiz;
import com.easypay.cornucopiaallqrpay.dal.dao.impl.TMchInfoMapperImpl;
import com.easypay.cornucopiaallqrpay.vo.response.CreateOrderResponse;
import com.easypay.cornucopiacommon.constant.PayConstant;
import com.easypay.cornucopiacommon.result.CommonResult;
import com.easypay.cornucopiacommon.result.ResultCode;
import com.easypay.cornucopiacommon.utils.AmountUtil;
import com.easypay.cornucopiacommon.utils.EncryptUtil;
import com.easypay.cornucopiacommon.utils.RSAEncryptUtil;
import com.easypay.cornucopiacommon.utils.XXPayUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    private CheckMerOrdIdBiz checkMerOrdIdBiz;

    private Map createPayOrder(String ordAmt ,Map<String, String> params, String mchId) {
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("mchId", mchId);                       // 商户ID
        paramMap.put("channelId", params.get("channelId"));             // 支付渠道ID, WX_NATIVE,ALIPAY_WAP
        paramMap.put("amount", ordAmt);                          // 支付金额,单位分
        paramMap.put("currency", "cny");                    // 币种, cny-人民币
        paramMap.put("clientIp", params.get("clientIp"));        // 用户地址,IP或手机号
        paramMap.put("device", "BAR");                      // 设备
        paramMap.put("subject", "反扫刷脸支付");
        paramMap.put("body", "反扫刷脸支付");
        paramMap.put("mchOrderId", params.get("mchOrderId"));
        paramMap.put("param1", params.get("scene"));                         // 扩展参数1
        paramMap.put("param2", params.get("authCode"));                         // 扩展参数2
        paramMap.put("deviceSn", params.get("deviceSn"));                         // 扩展参数2
        paramMap.put("traceId", MDC.get("traceId"));

        try {
            String reqSign = EncryptUtil.encrypt(RSAEncryptUtil.sign(paramMap, myprivatekey)).getCipherText();
            paramMap.put("sign", reqSign);   // 签名
        } catch (Exception e) {
            log.info("签名异常", e.getMessage());
            return null;
        }

        String reqData = "params=" + JSONObject.toJSONString(paramMap);
        log.info("请求支付中心下单接口,请求数据:" + JSONObject.toJSONString(paramMap));
        String url = defaultRequestValue.getBaseUrl() + "/api/pay/create_order?";
        String result = XXPayUtil.call4Post(url + reqData);
        if(result == null){
            log.info("=========支付中心下单异常=========");
            return null;
        }
        log.info("请求支付中心下单接口,响应数据:" + result);
        Map retMap = JSON.parseObject(result);
        if ("SUCCESS".equals(retMap.get("retCode"))) {
           log.info("支付中心下单成功");
        }else{
            log.info("支付中心下单失败");
            return null;
        }
        return retMap;
    }

    @RequestMapping("/qrpay")
    public CommonResult qrPay(HttpServletRequest request, HttpServletResponse response) {
        CreateOrderResponse createOrderResponse = new CreateOrderResponse();
        String logPrefix = "【条码支付】";
        String scene = request.getParameter("scene");
        String authCode = request.getParameter("authCode");
        String payAmt = request.getParameter("payAmt");
        String mchId  = request.getParameter("mchId");
        String channelId  = request.getParameter("channelId");
        String mchOrderId  = request.getParameter("mchOrderId");
        String deviceSn  = request.getParameter("deviceSn");


        if (StringUtils.isBlank(mchId) || StringUtils.isBlank(payAmt) ||
                StringUtils.isBlank(scene) || StringUtils.isBlank(authCode)||
                StringUtils.isBlank(channelId)||  StringUtils.isBlank(mchOrderId)
                ||  StringUtils.isBlank(deviceSn)) {
            log.info("{}请求参数错误,{}", logPrefix,"必填参数不可为空");
            return CommonResult.failed(ResultCode.CODE_999,"必填参数不可为空");
        }
        if (!channelId.equals(PayConstant.PAY_CHANNEL_ALIPAY_BAR_CODE) &&  !channelId.equals(PayConstant.PAY_CHANNEL_WX_BAR_CODE) ) {
            log.info("{}请求参数错误,{}", logPrefix,"必填参数channelId不合法");
            return CommonResult.failed(ResultCode.CODE_999,"channelId不合法");
        }


        String ordAmt = AmountUtil.convertDollar2Cent(payAmt);
        ResultCode respCode = checkMerIdBiz.checkMer(mchId);
        if (!ResultCode.SUCCESS.equals(respCode)) {
           log.info("{}请求参数错误,{}", logPrefix,"商户号不合法");
           return CommonResult.failed(ResultCode.CODE_999,"商户号不合法");
       }
        respCode = checkMerOrdIdBiz.checkMerOrdId(mchId,mchOrderId);
        if (!ResultCode.SUCCESS.equals(respCode)) {
            log.info("{}请求参数错误,{}", logPrefix,respCode.getMessage());
            return CommonResult.failed(respCode);
        }

        // 先插入订单数据
        Map<String, String> orderMap = null;
        Map<String, String> params = new  HashMap<>();
        params.put("channelId",channelId);
        params.put("scene",scene);
        params.put("mchOrderId",mchOrderId);
        params.put("authCode",authCode);
        params.put("clientIp",String.valueOf(request.getAttribute("clientIp")));
        params.put("deviceSn",deviceSn);

        orderMap = createPayOrder(ordAmt, params, mchId);
        if(orderMap == null){
            return CommonResult.failed(ResultCode.CODE_202);
        }
        JSONObject jsonObject = JSONObject.parseObject(orderMap.get("payUrl"));
        createOrderResponse.setMchId(mchId);
        createOrderResponse.setOrderInfo(orderMap.get("payUrl"));
        createOrderResponse.setMchOrderId(mchOrderId);
        if ("2".equals(jsonObject.getString("ordStatus"))){
            return CommonResult.success(JSONObject.toJSONString(createOrderResponse));
        }else if ("9".equals(jsonObject.getString("ordStatus"))){
            return CommonResult.failed(ResultCode.CODE_202.getCode(),ResultCode.CODE_202.getMessage(),JSONObject.toJSONString(createOrderResponse));
        }else if ("1".equals(jsonObject.getString("ordStatus"))){
            return CommonResult.failed(ResultCode.CODE_203.getCode(),ResultCode.CODE_203.getMessage(),JSONObject.toJSONString(createOrderResponse));
        }else {
            return CommonResult.failed(ResultCode.CODE_202);
        }

    }

}