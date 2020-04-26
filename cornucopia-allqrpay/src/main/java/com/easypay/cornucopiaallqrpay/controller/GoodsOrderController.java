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
import com.easypay.cornucopiacommon.result.ResultCode;
import com.easypay.cornucopiacommon.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/goods")
public class GoodsOrderController {
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

    private Map createPayOrder(String ordAmt ,Map<String, Object> params, String mchId,String clientIp) {
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("mchId", mchId);                       // 商户ID
        paramMap.put("channelId", params.get("channelId"));             // 支付渠道ID, WX_NATIVE,ALIPAY_WAP
        paramMap.put("amount", ordAmt);                          // 支付金额,单位分
        paramMap.put("currency", "cny");                    // 币种, cny-人民币
        paramMap.put("clientIp", clientIp);        // 用户地址,IP或手机号
        paramMap.put("device", "WEB");                      // 设备
        paramMap.put("subject", "聚合扫码支付");
        paramMap.put("body", "聚合扫码支付");
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
            log.info("=========支付中心下单成功=========");
        }else{
            log.info("=========支付中心下单失败=========");
            return null;
        }
        return retMap;
    }

    /**
     * 创建二维码接口
     *
     * @param model
     * @param mchId
     * @return
     */
    @RequestMapping("/createQrPay.html")
    public String openQrPay(ModelMap model, String mchId) {
        if (StringUtils.isBlank(mchId)) {
            model.put("dealMessage", "请求参数错误");
            return "dealMessage";
        }
        ResultCode respCode = checkMerIdBiz.checkMer(mchId);
        if (!ResultCode.SUCCESS.equals(respCode)) {
            log.info(respCode.getMessage());
            model.put("dealMessage", "商户号不合法");
            return "dealMessage";
        }

        TMchInfo tMchInfo = tMchInfoMapper.selectByMchId(mchId);
        try {
            HashMap map = new HashMap(2);
            map.put("mchId", mchId);
            map.put("key", tMchInfo.getRandomKey());

            model.put("baseUrl", defaultRequestValue.getBaseUrl());
            model.put("value", EncryptUtil.encrypt(RSAEncryptUtil.sign(map, privatekey)).getCipherText());
            model.put("mchId", mchId);
        } catch (Exception e) {
            log.info("签名异常");
            model.put("dealMessage", "系统异常");
            return "dealMessage";
        }
        return "createQrPay";
    }

    /**
     * 支付金额输入接口
     *
     * @param model
     * @param mchId
     * @return
     */
    @RequestMapping("/payOrder.html")
    public String payOrder(ModelMap model, String mchId, String value) {
        if (StringUtils.isBlank(mchId) || StringUtils.isBlank(value)) {
            model.put("dealMessage", "请求参数错误");
            return "dealMessage";
        }

        ResultCode respCode = checkMerIdBiz.checkMer(mchId);
        if (!ResultCode.SUCCESS.equals(respCode)) {
            log.info(respCode.getMessage());
            model.put("dealMessage", "商户号不合法");
            return "dealMessage";
        }

        TMchInfo tMchInfo = tMchInfoMapper.selectByMchId(mchId);
        try {
            HashMap mapSign = new HashMap(2);
            mapSign.put("mchId", mchId);
            mapSign.put("key", tMchInfo.getRandomKey());
            boolean checkRestlt = RSAEncryptUtil.verify(mapSign, publickey, EncryptUtil.decrypt(value).getPlainText());
            if (!checkRestlt) {
                log.info("验签失败");
                model.put("dealMessage", "数据不合法");
                return "dealMessage";
            }
            mapSign.put("key", RandomStrUtils.getInstance().getRandomString());

            model.put("mchId", mchId);
            model.put("key", mapSign.get("key"));
            model.put("checkValue", EncryptUtil.encrypt(RSAEncryptUtil.sign(mapSign, privatekey)).getCipherText());

        } catch (Exception e) {
            log.info("验签异常");
            model.put("dealMessage", "系统异常");
            return "dealMessage";
        }
        model.put("mchId", mchId);
        model.put("mchName",tMchInfo.getName());
        return "payOrder";
    }

    @RequestMapping("/qrPay.html")
    public String qrPay(ModelMap model, HttpServletRequest request) {
        String logPrefix = "【二维码扫码支付】";
        String payAmt = request.getParameter("payAmt");
        String mchId  = request.getParameter("mchId");
        String key  = request.getParameter("key");
        String checkValue  = request.getParameter("checkValue");
        if (StringUtils.isBlank(mchId) || StringUtils.isBlank(payAmt) || StringUtils.isBlank(key) || StringUtils.isBlank(checkValue)) {
            model.put("dealMessage", "请求参数错误");
            return "dealMessage";
        }
        String ordAmt = AmountUtil.convertDollar2Cent(payAmt);
        ResultCode respCode = checkMerIdBiz.checkMer(mchId);
        if (!ResultCode.SUCCESS.equals(respCode)) {
            log.info(respCode.getMessage());
            model.put("dealMessage", "输入金额错误");
            return "dealMessage";
        }
        HashMap mapSign = new HashMap(2);
        mapSign.put("mchId", mchId);
        mapSign.put("key", key);
        try {
            boolean checkRestlt = RSAEncryptUtil.verify(mapSign, publickey, EncryptUtil.decrypt(checkValue).getPlainText());
            if (!checkRestlt) {
                log.info("验签失败");
                model.put("dealMessage", "数据不合法");
                return "dealMessage";
            }
        } catch (Exception e) {
            log.info("验签异常");
            model.put("dealMessage", "系统异常");
            return "dealMessage";
        }

        String view = "qrPay";
        String ua = request.getHeader("User-Agent");
        String goodsId = "10001";
        log.info("{}接收参数:goodsId={},amount={},ua={}", logPrefix, goodsId, payAmt, ua);
        String client = "alipay";
        String channelId = "ALIPAY_WAP";
        if (StringUtils.isBlank(ua)) {
            String errorMessage = "User-Agent为空！";
            log.info("{}信息：{}", logPrefix, errorMessage);
            model.put("result", "failed");
            model.put("resMsg", errorMessage);
            return view;
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
            model.put("result", "failed");
            model.put("resMsg", errorMessage);
            return view;
        }
        // 先插入订单数据
        String clientIp = "47.98.210.13";
        Map<String, String> orderMap = null;
        if ("alipay".equals(client)) {
            log.info("{}{}扫码下单", logPrefix, "支付宝");
            Map params = new HashMap<>();
            params.put("channelId", channelId);
            // 下单
            orderMap = createPayOrder(ordAmt,params, mchId,clientIp);
        } else if ("wx".equals(client)) {
            log.info("{}{}扫码", logPrefix, "微信");
            // 判断是否拿到openid，如果没有则去获取
            String openId = request.getParameter("openId");
            //openId = "oP74lwuq0mzh19MZ9KLmr456ApoA";
            log.info("用户opedId={}", openId);
            if (StringUtils.isNotBlank(openId)) {
                log.info("{}openId：{}", logPrefix, openId);
                Map params = new HashMap<>();
                params.put("channelId", channelId);
                params.put("openId", openId);
                // 下单
                orderMap = createPayOrder(ordAmt, params, mchId,clientIp);
            } else {
                try {
                    mapSign = new HashMap(2);
                    mapSign.put("mchId", mchId);
                    mapSign.put("key", RandomStrUtils.getInstance().getRandomString());

                    StringBuffer redirectUrl = new StringBuffer(defaultRequestValue.getBaseUrl());
                    redirectUrl.append("/goods/getOpenId?redirectUrl=");
                    redirectUrl.append(defaultRequestValue.getBaseUrl()).append("/goods/qrPay.html?")
                            .append("payAmt=").append(payAmt)
                            .append("&mchId=").append(mchId)
                            .append("&key=").append(mapSign.get("key"))
                            .append("&checkValue=").append(EncryptUtil.encrypt(RSAEncryptUtil.sign(mapSign, privatekey)).getCipherText());
                    log.info("跳转URL={}", redirectUrl.toString());
                    return "redirect:" + redirectUrl.toString();
                } catch (Exception e) {
                    log.info("签名异常");
                    model.put("dealMessage", "系统异常");
                    return "dealMessage";
                }
            }
        }
        model.put("payAmt", payAmt);
        if (orderMap != null) {
            model.put("orderMap", orderMap);
        }
        TMchInfo tMchInfo = tMchInfoMapper.selectByMchId(mchId);
        model.put("mchName", tMchInfo.getName());
        model.put("client", client);
        return view;
    }

    /**
     * 获取code
     *
     * @return
     */
    @RequestMapping("/getOpenId")
    public void getOpenId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("进入获取用户openID页面");
        String redirectUrl = request.getParameter("redirectUrl");
        String code = request.getParameter("code");
        String openId = "";
        if (!StringUtils.isBlank(code)) {//如果request中包括code，则是微信回调
            try {
                openId = WxApiClient.getOAuthOpenId(AppID, AppSecret, code);
                log.info("调用微信返回openId={}", openId);
            } catch (Exception e) {
                log.error(e.getMessage(), "调用微信查询openId异常");
            }
            StringBuffer redirectUrlStringBuffer = new StringBuffer(redirectUrl);
            Map<String,String> paramMap = OAuth2RequestParamHelper.getStateParam(request);
            redirectUrlStringBuffer.append("&openId=").append(openId);
            for (String key:paramMap.keySet()){
                if(key.equals("payAmt") || key.equals("redirectUrl"))
                    continue;
                String value = paramMap.get(key);
                redirectUrlStringBuffer.append("&").append(key).append("=").append(value);
            }
            log.info("最终请求地址为：{}",redirectUrlStringBuffer.toString());
            response.sendRedirect(redirectUrlStringBuffer.toString());
        } else {//oauth获取code
            String redirectUrl4Vx = defaultRequestValue.getBaseUrl() + "/goods/getOpenId" + "?redirectUrl=" + redirectUrl;
            String state = OAuth2RequestParamHelper.prepareState(request);
            String url = WxApi.getOAuthCodeUrl(AppID, redirectUrl4Vx, "snsapi_base", state);
            log.info("跳转URL={}", url);
            response.sendRedirect(url);
        }
    }

}