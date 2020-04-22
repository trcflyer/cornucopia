package com.easypay.cornucopiaallqrpay.controller;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.easypay.cornucopiaallqrpay.DefaultRequestValue;
import com.easypay.cornucopiaallqrpay.biz.CheckMerIdBiz;
import com.easypay.cornucopiaallqrpay.dal.dao.impl.TMchInfoMapperImpl;
import com.easypay.cornucopiaallqrpay.dal.pojo.TMchInfo;
import com.easypay.cornucopiaallqrpay.util.OAuth2RequestParamHelper;
import com.easypay.cornucopiaallqrpay.util.vx.WxApi;
import com.easypay.cornucopiaallqrpay.util.vx.WxApiClient;
import com.easypay.cornucopiaallqrpay.vo.request.CreateOrderRequest;
import com.easypay.cornucopiaallqrpay.vo.response.CreateOrderResponse;
import com.easypay.cornucopiacommon.enums.RespCode;
import com.easypay.cornucopiacommon.result.CommonResult;
import com.easypay.cornucopiacommon.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 反扫刷脸支付接口
 */
@Slf4j
@Controller
@RequestMapping("/face/qrcode")
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

    static final String AppID = "wx94099cff69f2c74e";
    static final String AppSecret = "0efb27b66c84c449858cfe5d09d5f73c";

    private Map createPayOrder(String ordAmt ,Map<String, Object> params, String mchId) {
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("mchId", mchId);                       // 商户ID
        paramMap.put("channelId", params.get("channelId"));             // 支付渠道ID, WX_NATIVE,ALIPAY_WAP
        paramMap.put("amount", ordAmt);                          // 支付金额,单位分
        paramMap.put("currency", "cny");                    // 币种, cny-人民币
        paramMap.put("clientIp", "114.112.124.236");        // 用户地址,IP或手机号
        paramMap.put("device", "WEB");                      // 设备
        paramMap.put("subject", "反扫刷脸支付");
        paramMap.put("body", "反扫刷脸支付");
        paramMap.put("notifyUrl", defaultRequestValue.getBaseUrl() + "/goods/payNotify");         // 回调URL
        paramMap.put("param1", "");                         // 扩展参数1
        paramMap.put("param2", "");                         // 扩展参数2

        JSONObject extra = new JSONObject();
        extra.put("openId", params.get("openId"));
        paramMap.put("extra", extra.toJSONString());  // 附加参数
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
            // 验签
            String checkSign = PayDigestUtil.getSign(retMap, publickey, "sign", "payParams");
            String retSign = (String) retMap.get("sign");
            if (checkSign.equals(retSign)) {
                log.info("=========支付中心下单验签成功=========");
            } else {
                System.err.println("=========支付中心下单验签失败=========");
            }
        }
        return retMap;
    }

    /**
     * 创建二维码接口
     *
     * @param request
     * @return
     */
    @RequestMapping("/createOrder")
    @ResponseBody
    public CommonResult openQrPay(CreateOrderRequest request) {
        CreateOrderResponse response = new CreateOrderResponse();
        HashMap mapSign = new HashMap(2);
        mapSign.put("memberId", request.getMemberId());
        try {
            boolean checkRestlt = RSAEncryptUtil.verify(mapSign, publickey, EncryptUtil.decrypt(request.getCheckValue()).getPlainText());
            if (!checkRestlt) {
                log.info("验签失败");
                response.setRespCode(RespCode.CODE_999.getRespCode());
                response.setRespDesc(RespCode.CODE_999.getRespCode());
                return CommonResult.failed(JSONObject.toJSONString(response));
            }
        } catch (Exception e) {
            log.info("验签异常");
            response.setRespCode(RespCode.CODE_998.getRespCode());
            response.setRespDesc(RespCode.CODE_998.getRespCode());
            return CommonResult.failed(JSONObject.toJSONString(response));
        }

        response.setRespCode(RespCode.CODE_000.getRespCode());
        response.setRespDesc(RespCode.CODE_000.getRespCode());
        return CommonResult.failed(JSONObject.toJSONString(response));
    }

    @RequestMapping("/qrPay")
    public CommonResult qrPay( HttpServletRequest request) {
        CreateOrderResponse response = new CreateOrderResponse();
        String logPrefix = "【二维码扫码支付】";
        String payAmt = request.getParameter("payAmt");
        String mchId  = request.getParameter("mchId");
        String key  = request.getParameter("key");
        String checkValue  = request.getParameter("checkValue");

        String ordAmt = AmountUtil.convertDollar2Cent(payAmt);
        RespCode respCode = checkMerIdBiz.checkMer(mchId);
        if (!RespCode.CODE_000.getRespCode().equals(respCode.getRespCode())) {
            log.info(respCode.getRespDesc());
            response.setRespCode(RespCode.CODE_999.getRespCode());
            response.setRespDesc(RespCode.CODE_999.getRespCode());
            return CommonResult.failed(JSONObject.toJSONString(response));
        }
        HashMap mapSign = new HashMap(2);
        mapSign.put("mchId", mchId);
        mapSign.put("key", key);
        try {
            boolean checkRestlt = RSAEncryptUtil.verify(mapSign, publickey, EncryptUtil.decrypt(checkValue).getPlainText());
            if (!checkRestlt) {
                log.info("验签失败");
                response.setRespCode(RespCode.CODE_999.getRespCode());
                response.setRespDesc(RespCode.CODE_999.getRespCode());
                return CommonResult.failed(JSONObject.toJSONString(response));
            }
        } catch (Exception e) {
            log.info("验签异常");
            response.setRespCode(RespCode.CODE_998.getRespCode());
            response.setRespDesc(RespCode.CODE_998.getRespCode());
            return CommonResult.failed(JSONObject.toJSONString(response));
        }

        String view = "qrPay";
        log.info("====== 开始接收二维码扫码支付请求 ======");
        String ua = request.getHeader("User-Agent");
        log.info("{}接收参数:amount={},ua={}", logPrefix, payAmt, ua);
        String client = "alipay";
        String channelId = "ALIPAY_WAP";
        if (StringUtils.isBlank(ua)) {
            String errorMessage = "User-Agent为空！";
            log.info("{}信息：{}", logPrefix, errorMessage);
            response.setRespCode(RespCode.CODE_998.getRespCode());
            response.setRespDesc(RespCode.CODE_998.getRespCode());
            return CommonResult.failed(JSONObject.toJSONString(response));
        } else {
            if (ua.contains("Alipay")) {
                client = "alipay";
                channelId = "ALIPAY_WAP";
            } else if (ua.contains("MicroMessenger")) {
                client = "wx";
                channelId = "WX_JSAPI";
            }
        }
        if (client == null) {
            String errorMessage = "请用微信或支付宝扫码";
            log.info("{}信息：{}", logPrefix, errorMessage);
            response.setRespCode(RespCode.CODE_998.getRespCode());
            response.setRespDesc(RespCode.CODE_998.getRespCode());
            return CommonResult.failed(JSONObject.toJSONString(response));
        }
        // 先插入订单数据
        Map<String, String> orderMap = null;
        if ("alipay".equals(client)) {
            log.info("{}{}扫码下单", logPrefix, "支付宝");
            Map params = new HashMap<>();
            params.put("channelId", channelId);
            // 下单
            orderMap = createPayOrder(ordAmt,params, mchId);
        } else if ("wx".equals(client)) {
            log.info("{}{}扫码", logPrefix, "微信");
            // 判断是否拿到openid，如果没有则去获取
            String openId = request.getParameter("openId");
            Map params = new HashMap<>();
            params.put("channelId", channelId);
            params.put("openId", openId);
            // 下单
            orderMap = createPayOrder(ordAmt, params, mchId);
        }
        response.setRespCode(RespCode.CODE_000.getRespCode());
        response.setRespDesc(RespCode.CODE_000.getRespCode());
        return CommonResult.success(JSONObject.toJSONString(response));
    }

}