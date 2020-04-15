package com.easypay.cornucopiaallqrpay.controller;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.easypay.cornucopiaallqrpay.DefaultRequestValue;
import com.easypay.cornucopiaallqrpay.biz.CheckMerIdBiz;
import com.easypay.cornucopiaallqrpay.biz.SequenceBiz;
import com.easypay.cornucopiaallqrpay.dal.dao.impl.TMchInfoMapperImpl;
import com.easypay.cornucopiaallqrpay.dal.pojo.TGoodsOrder;
import com.easypay.cornucopiaallqrpay.dal.pojo.TMchInfo;
import com.easypay.cornucopiaallqrpay.service.GoodsOrderService;
import com.easypay.cornucopiaallqrpay.util.Constant;
import com.easypay.cornucopiaallqrpay.util.OAuth2RequestParamHelper;
import com.easypay.cornucopiaallqrpay.util.vx.WxApi;
import com.easypay.cornucopiaallqrpay.util.vx.WxApiClient;
import com.easypay.cornucopiacommon.constant.PayConstant;
import com.easypay.cornucopiacommon.enums.RespCode;
import com.easypay.cornucopiacommon.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/goods")
public class GoodsOrderController {


    @Autowired
    private GoodsOrderService goodsOrderService;

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
    private SequenceBiz sequenceBiz;

    static final String AppID = "wx94099cff69f2c74e";
    static final String AppSecret = "0efb27b66c84c449858cfe5d09d5f73c";

    private Map createPayOrder(TGoodsOrder goodsOrder, Map<String, Object> params, String mchId) {
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("mchId", mchId);                       // 商户ID
        paramMap.put("mchOrderNo", goodsOrder.getGoodsOrderId());           // 商户订单号
        paramMap.put("channelId", params.get("channelId"));             // 支付渠道ID, WX_NATIVE,ALIPAY_WAP
        paramMap.put("amount", goodsOrder.getAmount());                          // 支付金额,单位分
        paramMap.put("currency", "cny");                    // 币种, cny-人民币
        paramMap.put("clientIp", "114.112.124.236");        // 用户地址,IP或手机号
        paramMap.put("device", "WEB");                      // 设备
        paramMap.put("subject", "聚合扫码支付");
        paramMap.put("body", "聚合扫码支付");
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
        RespCode respCode = checkMerIdBiz.checkMer(mchId);
        if (!RespCode.CODE_000.getRespCode().equals(respCode.getRespCode())) {
            log.info(respCode.getRespDesc());
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

        RespCode respCode = checkMerIdBiz.checkMer(mchId);
        if (!RespCode.CODE_000.getRespCode().equals(respCode.getRespCode())) {
            log.info(respCode.getRespDesc());
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
        RespCode respCode = checkMerIdBiz.checkMer(mchId);
        if (!RespCode.CODE_000.getRespCode().equals(respCode.getRespCode())) {
            log.info(respCode.getRespDesc());
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
        log.info("====== 开始接收二维码扫码支付请求 ======");
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
        TGoodsOrder goodsOrder = null;
        Map<String, String> orderMap = null;
        if ("alipay".equals(client)) {
            log.info("{}{}扫码下单", logPrefix, "支付宝");
            Map params = new HashMap<>();
            params.put("channelId", channelId);
            // 下单
            goodsOrder = createGoodsOrder( Long.valueOf(ordAmt), mchId);
            orderMap = createPayOrder(goodsOrder, params, mchId);
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
                goodsOrder = createGoodsOrder( Long.valueOf(ordAmt), mchId);
                // 下单
                orderMap = createPayOrder(goodsOrder, params, mchId);
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
        model.put("goodsOrder", goodsOrder);
        model.put("amount", AmountUtil.convertCent2Dollar(goodsOrder.getAmount() + ""));
        if (orderMap != null) {
            model.put("orderMap", orderMap);
            String payOrderId = orderMap.get("payOrderId");
            TGoodsOrder go = new TGoodsOrder();
            go.setGoodsOrderId(goodsOrder.getGoodsOrderId());
            go.setPayOrderId(payOrderId);
            int ret = goodsOrderService.update(go);
            log.info("修改商品订单,返回:{}", ret);
        }
        TMchInfo tMchInfo = tMchInfoMapper.selectByMchId(mchId);
        model.put("mchName", tMchInfo.getName());
        model.put("client", client);
        return view;
    }

    TGoodsOrder createGoodsOrder( Long amount, String mchId) {
        if (null == amount) {
            amount = 1L;
        }
        String ordIdSqe = sequenceBiz.getSeqId("ORD_ID_SQE");
        // 先插入订单数据
        String sqeId = StringUtils.leftPad(ordIdSqe, 8, "0");
        TGoodsOrder goodsOrder = new TGoodsOrder();
        goodsOrder.setTransDate(DateUtils.getCurrentTimeStrDefault().substring(0,8));
        goodsOrder.setSqeId(sqeId);
        goodsOrder.setGoodsOrderId(goodsOrder.getTransDate()+goodsOrder.getSqeId());
        goodsOrder.setAmount(amount);
        goodsOrder.setMchId(mchId);
        goodsOrder.setTransState(Constant.GOODS_ORDER_STATUS_INIT);
        int result = goodsOrderService.addGoodsOrder(goodsOrder);
        log.info("插入商品订单,返回:{}", result);
        return goodsOrder;
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

    /**
     * 接收支付中心通知
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/payNotify")
    public void payNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("====== 开始处理支付中心通知 ======");
        Map<String, Object> paramMap = request2payResponseMap(request, new String[]{
                "payOrderId", "mchId", "mchOrderNo", "channelId", "amount", "currency", "status", "clientIp",
                "device", "subject", "channelOrderNo", "param1",
                "param2", "paySuccTime", "backType", "sign"
        });
        log.info("支付中心通知请求参数,paramMap={}", paramMap);
        if (!verifyPayResponse(paramMap)) {
            String errorMessage = "verify request param failed.";
            log.warn(errorMessage);
            outResult(response, "fail");
            return;
        }
        String payOrderId = (String) paramMap.get("payOrderId");
        String mchOrderNo = (String) paramMap.get("mchOrderNo");
        String resStr;
        try {
            TGoodsOrder goodsOrder = goodsOrderService.getGoodsOrder(mchOrderNo);
            if (goodsOrder != null && goodsOrder.getTransState() == Constant.GOODS_ORDER_STATUS_COMPLETE) {
                outResult(response, "success");
                return;
            }
            // 执行业务逻辑
            int ret = goodsOrderService.updateStatus4Success(mchOrderNo);
            // ret返回结果
            // 等于1表示处理成功,返回支付中心success
            // 其他值,返回支付中心fail,让稍后再通知
            if (ret == 1) {
                ret = goodsOrderService.updateStatus4Complete(mchOrderNo);
                if (ret == 1) {
                    resStr = "success";
                } else {
                    resStr = "fail";
                }
            } else {
                resStr = "fail";
            }
        } catch (Exception e) {
            resStr = "fail";
            log.error(e.getMessage(), "执行业务异常,payOrderId=%s.mchOrderNo=%s", payOrderId, mchOrderNo);
        }
        log.info("响应支付中心通知结果:{},payOrderId={},mchOrderNo={}", resStr, payOrderId, mchOrderNo);
        outResult(response, resStr);
        log.info("====== 支付中心通知处理完成 ======");
    }

    @RequestMapping("/notify_test")
    public void notifyTest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        outResult(response, "success");
    }

    @RequestMapping("/toAliPay.html")
    @ResponseBody
    public String toAliPay(HttpServletRequest request, Long amount, String channelId, String mchId) {
        String logPrefix = "【支付宝支付】";
        log.info("====== 开始接收支付宝支付请求 ======");
        log.info("{}接收参数:amount={},channelId={}", logPrefix, amount, channelId);
        // 先插入订单数据
        Map params = new HashMap<>();
        params.put("channelId", channelId);
        // 下单
        TGoodsOrder goodsOrder = createGoodsOrder( amount, mchId);
        Map<String, String> orderMap = createPayOrder(goodsOrder, params, mchId);
        if (orderMap != null && "success".equalsIgnoreCase(orderMap.get("resCode"))) {
            String payOrderId = orderMap.get("payOrderId");
            TGoodsOrder go = new TGoodsOrder();
            go.setGoodsOrderId(goodsOrder.getGoodsOrderId());
            go.setPayOrderId(payOrderId);
            int ret = goodsOrderService.update(go);
            log.info("修改商品订单,返回:{}", ret);
        }
        if (PayConstant.PAY_CHANNEL_ALIPAY_MOBILE.equalsIgnoreCase(channelId)) return orderMap.get("payParams");
        return orderMap.get("payUrl");
    }

    void outResult(HttpServletResponse response, String content) {
        response.setContentType("text/html");
        PrintWriter pw;
        try {
            pw = response.getWriter();
            pw.print(content);
            log.error("response xxpay complete.");
        } catch (IOException e) {
            log.error(e.getMessage(), "response xxpay write exception.");
        }
    }

    public Map<String, Object> request2payResponseMap(HttpServletRequest request, String[] paramArray) {
        Map<String, Object> responseMap = new HashMap<>();
        for (int i = 0; i < paramArray.length; i++) {
            String key = paramArray[i];
            String v = request.getParameter(key);
            if (v != null) {
                responseMap.put(key, v);
            }
        }
        return responseMap;
    }

    public boolean verifyPayResponse(Map<String, Object> map) {
        String mchId = (String) map.get("mchId");
        String payOrderId = (String) map.get("payOrderId");
        String mchOrderNo = (String) map.get("mchOrderNo");
        String amount = (String) map.get("amount");
        String sign = (String) map.get("sign");

        if (StringUtils.isEmpty(mchId)) {
            log.warn("Params error. mchId={}", mchId);
            return false;
        }
        if (StringUtils.isEmpty(payOrderId)) {
            log.warn("Params error. payOrderId={}", payOrderId);
            return false;
        }
        if (StringUtils.isEmpty(amount) || !NumberUtils.isNumber(amount)) {
            log.warn("Params error. amount={}", amount);
            return false;
        }
        if (StringUtils.isEmpty(sign)) {
            log.warn("Params error. sign={}", sign);
            return false;
        }

        // 验证签名
        if (!verifySign(map)) {
            log.warn("verify params sign failed. payOrderId={}", payOrderId);
            return false;
        }

        // 根据payOrderId查询业务订单,验证订单是否存在
        TGoodsOrder goodsOrder = goodsOrderService.getGoodsOrder(mchOrderNo);
        if (goodsOrder == null) {
            log.warn("业务订单不存在,payOrderId={},mchOrderNo={}", payOrderId, mchOrderNo);
            return false;
        }
        // 核对金额
        if (goodsOrder.getAmount() != Long.parseLong(amount)) {
            log.warn("支付金额不一致,dbPayPrice={},payPrice={}", goodsOrder.getAmount(), amount);
            return false;
        }
        return true;
    }

    public boolean verifySign(Map<String, Object> map) {
        String mchId = (String) map.get("mchId");
        TMchInfo tMchInfo = tMchInfoMapper.selectByMchId(mchId);

        String localSign = PayDigestUtil.getSign(map, publickey, "sign");
        String sign = (String) map.get("sign");
        return localSign.equalsIgnoreCase(sign);
    }

}