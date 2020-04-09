package com.easypay.cornucopiaallqrpay.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.easypay.cornucopiaallqrpay.dal.pojo.TGoodsOrder;
import com.easypay.cornucopiaallqrpay.service.GoodsOrderService;
import com.easypay.cornucopiaallqrpay.util.Constant;
import com.easypay.cornucopiaallqrpay.util.DateUtil;
import com.easypay.cornucopiaallqrpay.util.OAuth2RequestParamHelper;
import com.easypay.cornucopiaallqrpay.util.vx.WxApi;
import com.easypay.cornucopiaallqrpay.util.vx.WxApiClient;
import com.easypay.cornucopiacommon.constant.PayConstant;
import com.easypay.cornucopiacommon.utils.AmountUtil;
import com.easypay.cornucopiacommon.utils.PayDigestUtil;
import com.easypay.cornucopiacommon.utils.XXPayUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Controller
@RequestMapping("/goods")
public class GoodsOrderController {


    @Autowired
    private GoodsOrderService goodsOrderService;

    static final String MCHID = "20001223";
    // 加签key
    static final String reqKey = "M86l522AV6q613Ii4W6u8K48uW8vM1N6bFgyv769220MdYe9u37N4y7rI5mQ";
    // 验签key
    static final String resKey = "Hpcl522AV6q613KIi46u6g6XuW8vM1N8bFgyv769770MdYe9u37M4y7rIpl8";
    //static final String baseUrl = "http://api.xxpay.org/api";
    static final String baseUrl = "http://127.0.0.1:8082";
    private AtomicLong seq = new AtomicLong(0L);
    private final static String QR_PAY_URL = "http://127.0.0.1:8082/goods/qrPay.html";
    static final String AppID = "wx94099cff69f2c74e";
    static final String AppSecret = "0efb27b66c84c449858cfe5d09d5f73c";


    @RequestMapping(value = "/buy/{goodsId}", method = RequestMethod.GET)
    @ResponseBody
    public String buy(@PathVariable("goodsId") String goodsId) {
        if(!"G_0001".equals(goodsId)) {
            return "fail";
        }
        String goodsOrderId = String.format("%s%s%06d", "G", DateUtil.getSeqString(), (int) seq.getAndIncrement() % 1000000);
        TGoodsOrder goodsOrder = new TGoodsOrder();
        goodsOrder.setGoodsorderid(goodsOrderId);
        goodsOrder.setGoodsid(goodsId);
        goodsOrder.setGoodsname("XXPAY捐助商品G_0001");
        goodsOrder.setAmount(1l);
        goodsOrder.setUserid("xxpay_000001");
        goodsOrder.setStatus(Constant.GOODS_ORDER_STATUS_INIT);
        int result = goodsOrderService.addGoodsOrder(goodsOrder);
        log.info("插入商品订单,返回:{}", result);
        return result+"";
    }

    @RequestMapping(value = "/pay/{goodsOrderId}", method = RequestMethod.GET)
    @ResponseBody
    public String pay(@PathVariable("goodsOrderId") String goodsOrderId) {
        TGoodsOrder goodsOrder = goodsOrderService.getGoodsOrder(goodsOrderId);
        if(goodsOrder == null) return "fail";
        int status = goodsOrder.getStatus();
        if(status != Constant.GOODS_ORDER_STATUS_INIT) {
            return "fail_001";
        }
        JSONObject paramMap = new JSONObject();
        paramMap.put("mchId", MCHID);                       // 商户ID
        paramMap.put("mchOrderNo", goodsOrderId);           // 商户订单号
        paramMap.put("channelId", "ALIPAY_WAP");             // 支付渠道ID, WX_NATIVE,ALIPAY_WAP
        paramMap.put("amount", goodsOrder.getAmount());                          // 支付金额,单位分
        paramMap.put("currency", "cny");                    // 币种, cny-人民币
        paramMap.put("clientIp", "114.112.124.236");        // 用户地址,IP或手机号
        paramMap.put("device", "WEB");                      // 设备
        paramMap.put("subject", goodsOrder.getGoodsname());
        paramMap.put("body", goodsOrder.getGoodsname());
        paramMap.put("notifyUrl", baseUrl+"/goods/payNotify");         // 回调URL
        paramMap.put("param1", "");                         // 扩展参数1
        paramMap.put("param2", "");                         // 扩展参数2
        paramMap.put("extra", "{\"productId\":\"120989823\",\"openId\":\"o2RvowBf7sOVJf8kJksUEMceaDqo\"}");  // 附加参数

        String reqSign = PayDigestUtil.getSign(paramMap, reqKey);
        paramMap.put("sign", reqSign);   // 签名
        String reqData = "params=" + paramMap.toJSONString();
        System.out.println("请求支付中心下单接口,请求数据:" + reqData);
        String url = baseUrl + "/api/pay/create_order?";
        String result = XXPayUtil.call4Post(url + reqData);
        System.out.println("请求支付中心下单接口,响应数据:" + result);
        Map retMap = JSON.parseObject(result);
        if("SUCCESS".equals(retMap.get("retCode"))) {
            // 验签
            String checkSign = PayDigestUtil.getSign(retMap, resKey, "sign", "payParams");
            String retSign = (String) retMap.get("sign");
            if(checkSign.equals(retSign)) {
                System.out.println("=========支付中心下单验签成功=========");
            }else {
                System.err.println("=========支付中心下单验签失败=========");
                return null;
            }
        }
        String payOrderId = retMap.get("payOrderId").toString();

        goodsOrder = new TGoodsOrder();
        goodsOrder.setGoodsorderid(goodsOrderId);
        goodsOrder.setPayorderid(payOrderId);
        goodsOrder.setChannelid("ALIPAY_WAP");
        int ret = goodsOrderService.update(goodsOrder);
        log.info("修改商品订单,返回:{}", ret);
        return result+"";
    }

    private Map createPayOrder(TGoodsOrder goodsOrder, Map<String, Object> params,String mchId) {
        JSONObject paramMap = new JSONObject();
        paramMap.put("mchId",mchId );                       // 商户ID
        paramMap.put("mchOrderNo", goodsOrder.getGoodsorderid());           // 商户订单号
        paramMap.put("channelId", params.get("channelId"));             // 支付渠道ID, WX_NATIVE,ALIPAY_WAP
        paramMap.put("amount", goodsOrder.getAmount());                          // 支付金额,单位分
        paramMap.put("currency", "cny");                    // 币种, cny-人民币
        paramMap.put("clientIp", "114.112.124.236");        // 用户地址,IP或手机号
        paramMap.put("device", "WEB");                      // 设备
        paramMap.put("subject", goodsOrder.getGoodsname());
        paramMap.put("body", goodsOrder.getGoodsname());
        paramMap.put("notifyUrl", baseUrl+"/goods/payNotify");         // 回调URL
        paramMap.put("param1", "");                         // 扩展参数1
        paramMap.put("param2", "");                         // 扩展参数2

        JSONObject extra = new JSONObject();
        extra.put("openId", params.get("openId"));
        paramMap.put("extra", extra.toJSONString());  // 附加参数

        String reqSign = PayDigestUtil.getSign(paramMap, reqKey);
        paramMap.put("sign", reqSign);   // 签名
        String reqData = "params=" + paramMap.toJSONString();
        System.out.println("请求支付中心下单接口,请求数据:" + reqData);
        String url = baseUrl + "/api/pay/create_order?";
        String result = XXPayUtil.call4Post(url + reqData);
        System.out.println("请求支付中心下单接口,响应数据:" + result);
        Map retMap = JSON.parseObject(result);
        if("SUCCESS".equals(retMap.get("retCode"))) {
            // 验签
            String checkSign = PayDigestUtil.getSign(retMap, resKey, "sign", "payParams");
            String retSign = (String) retMap.get("sign");
            if(checkSign.equals(retSign)) {
                System.out.println("=========支付中心下单验签成功=========");
            }else {
                System.err.println("=========支付中心下单验签失败=========");
                //return null;
            }
        }
        return retMap;
    }

    @RequestMapping("/openQrPay.html")
    public String openQrPay(ModelMap model,String mchId) {
        if(StringUtils.isBlank(mchId)){
            mchId = this.MCHID;
        }
        model.put("mchId", mchId);
        return "openQrPay";
    }
    @RequestMapping("/payOrder.html")
    public String payOrder(ModelMap model,String mchId ) {
        if(StringUtils.isBlank(mchId)){
            mchId = this.MCHID;
        }
        model.put("mchId", mchId);
        model.put("mchId", mchId);
        return "payOrder";
    }

    @RequestMapping("/qrPay.html")
    public String qrPay(ModelMap model, HttpServletRequest request, Long payAmt,String mchId) {
        String logPrefix = "【二维码扫码支付】";
        String view = "qrPay";
        log.info("====== 开始接收二维码扫码支付请求 ======");
        String ua = request.getHeader("User-Agent");
        String goodsId = "G_0001";
        log.info("{}接收参数:goodsId={},amount={},ua={}", logPrefix, goodsId, payAmt, ua);
        String client = "wx";
        String channelId = "WX_JSAPI";
        if(StringUtils.isBlank(ua)) {
            String errorMessage = "User-Agent为空！";
            log.info("{}信息：{}", logPrefix, errorMessage);
            model.put("result", "failed");
            model.put("resMsg", errorMessage);
            return view;
        }else {
            if(ua.contains("Alipay")) {
                client = "alipay";
                channelId = "ALIPAY_WAP";
            }else if(ua.contains("MicroMessenger")) {
                client = "wx";
                channelId = "WX_JSAPI";
            }
        }
        if(client == null) {
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
            goodsOrder = createGoodsOrder(goodsId, payAmt);
            orderMap = createPayOrder(goodsOrder, params,mchId);
        }else if("wx".equals(client)){
            log.info("{}{}扫码", logPrefix, "微信");
            // 判断是否拿到openid，如果没有则去获取
            String openId = request.getParameter("openId");
            if (StringUtils.isNotBlank(openId)) {
                log.info("{}openId：{}", logPrefix, openId);
                Map params = new HashMap<>();
                params.put("channelId", channelId);
                params.put("openId", openId);
                goodsOrder = createGoodsOrder(goodsId, payAmt);
                // 下单
                orderMap = createPayOrder(goodsOrder, params,mchId);
            }else {
                String redirectUrl = QR_PAY_URL + "?payAmt=" + payAmt+"&mchId="+mchId;
                String url = baseUrl+"/goods/getOpenId" + "?redirectUrl=" + redirectUrl;
                log.info("跳转URL={}", url);
                return "redirect:" + url;
            }
        }
        model.put("goodsOrder", goodsOrder);
        model.put("amount", AmountUtil.convertCent2Dollar(goodsOrder.getAmount()+""));
        if(orderMap != null) {
            model.put("orderMap", orderMap);
            String payOrderId = orderMap.get("payOrderId");
            TGoodsOrder go = new TGoodsOrder();
            go.setGoodsorderid(goodsOrder.getGoodsorderid());
            go.setPayorderid(payOrderId);
            go.setChannelid(channelId);
            int ret = goodsOrderService.update(go);
            log.info("修改商品订单,返回:{}", ret);
        }
        model.put("client", client);
        return view;
    }

    TGoodsOrder createGoodsOrder(String goodsId, Long amount) {
        if(null == amount){
            amount = 1L;
        }
        // 先插入订单数据
        String goodsOrderId =  String.format("%s%s%06d", "G", DateUtil.getSeqString(), (int) seq.getAndIncrement() % 1000000);
        TGoodsOrder goodsOrder = new TGoodsOrder();
        goodsOrder.setGoodsorderid(goodsOrderId);
        goodsOrder.setGoodsid(goodsId);
        goodsOrder.setGoodsname("XXPAY捐助商品G_0001");
        goodsOrder.setAmount(amount);
        goodsOrder.setUserid("xxpay_000001");
        goodsOrder.setStatus(Constant.GOODS_ORDER_STATUS_INIT);
        int result = goodsOrderService.addGoodsOrder(goodsOrder);
        log.info("插入商品订单,返回:{}", result);
        return goodsOrder;
    }

    /**
     * 获取code
     * @return
     */
    @RequestMapping("/getOpenId")
    public void getOpenId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("进入获取用户openID页面");
        String redirectUrl = request.getParameter("redirectUrl");
        String code = request.getParameter("code");
        String openId = "";
        if(!StringUtils.isBlank(code)){//如果request中包括code，则是微信回调
            try {
                openId = WxApiClient.getOAuthOpenId(AppID, AppSecret, code);
                log.info("调用微信返回openId={}", openId);
            } catch (Exception e) {
                log.error(e.getMessage(), "调用微信查询openId异常");
            }
            if(redirectUrl.indexOf("?") > 0) {
                redirectUrl += "&openId=" + openId;
            }else {
                redirectUrl += "?openId=" + openId;
            }
            response.sendRedirect(redirectUrl);
        }else{//oauth获取code
            String redirectUrl4Vx = baseUrl+"/goods/getOpenId" + "?redirectUrl=" + redirectUrl;
            String state = OAuth2RequestParamHelper.prepareState(request);
            String url = WxApi.getOAuthCodeUrl(AppID, redirectUrl4Vx, "snsapi_base", state);
            log.info("跳转URL={}", url);
            response.sendRedirect(url);
        }
    }

    /**
     * 获取code
     * @return
     */
    @RequestMapping("/getOpenId2")
    public void getOpenId2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("进入获取用户openID页面");
        String redirectUrl = request.getParameter("redirectUrl");
        String code = request.getParameter("code");
        String openId = "";
        if(!StringUtils.isBlank(code)){//如果request中包括code，则是微信回调
            try {
                openId = WxApiClient.getOAuthOpenId(AppID, AppSecret, code);
                log.info("调用微信返回openId={}", openId);
            } catch (Exception e) {
                log.error(e.getMessage(), "调用微信查询openId异常");
            }
            if(redirectUrl.indexOf("?") > 0) {
                redirectUrl += "&openId=" + openId;
            }else {
                redirectUrl += "?openId=" + openId;
            }
            response.sendRedirect(redirectUrl);
        }else{//oauth获取code
            //http://www.abc.com/xxx/get-weixin-code.html?appid=XXXX&scope=snsapi_base&state=hello-world&redirect_uri=http%3A%2F%2Fwww.xyz.com%2Fhello-world.html
            String redirectUrl4Vx = baseUrl+"/goods/getOpenId2" + "?redirectUrl=" + redirectUrl;
            String url = String.format("http://www.xiaoshuding.com/get-weixin-code.html?appid=%s&scope=snsapi_base&state=hello-world&redirect_uri=%s", AppID, WxApi.urlEnodeUTF8(redirectUrl4Vx));
            log.info("跳转URL={}", url);
            response.sendRedirect(url);
        }
    }

    /**
     * 接收支付中心通知
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/payNotify")
    public void payNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("====== 开始处理支付中心通知 ======");
        Map<String,Object> paramMap = request2payResponseMap(request, new String[]{
                "payOrderId","mchId","mchOrderNo","channelId","amount","currency","status", "clientIp",
                "device",  "subject", "channelOrderNo", "param1",
                "param2","paySuccTime","backType","sign"
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
            if(goodsOrder != null && goodsOrder.getStatus() == Constant.GOODS_ORDER_STATUS_COMPLETE) {
                outResult(response, "success");
                return;
            }
            // 执行业务逻辑
            int ret = goodsOrderService.updateStatus4Success(mchOrderNo);
            // ret返回结果
            // 等于1表示处理成功,返回支付中心success
            // 其他值,返回支付中心fail,让稍后再通知
            if(ret == 1) {
                ret = goodsOrderService.updateStatus4Complete(mchOrderNo);
                if(ret == 1) {
                    resStr = "success";
                }else {
                    resStr = "fail";
                }
            }else {
                resStr = "fail";
            }
        }catch (Exception e) {
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
    public String toAliPay(HttpServletRequest request, Long amount, String channelId) {
        String logPrefix = "【支付宝支付】";
        log.info("====== 开始接收支付宝支付请求 ======");
        String goodsId = "G_0001";
        log.info("{}接收参数:goodsId={},amount={},channelId={}", logPrefix, goodsId, amount, channelId);
        // 先插入订单数据
        Map params = new HashMap<>();
        params.put("channelId", channelId);
        // 下单
        TGoodsOrder goodsOrder = createGoodsOrder(goodsId, amount);
        Map<String, String> orderMap = createPayOrder(goodsOrder, params,MCHID);
        if(orderMap != null && "success".equalsIgnoreCase(orderMap.get("resCode"))) {
            String payOrderId = orderMap.get("payOrderId");
            TGoodsOrder go = new TGoodsOrder();
            go.setGoodsorderid(goodsOrder.getGoodsorderid());
            go.setPayorderid(payOrderId);
            go.setChannelid(channelId);
            int ret = goodsOrderService.update(go);
            log.info("修改商品订单,返回:{}", ret);
        }
        if(PayConstant.PAY_CHANNEL_ALIPAY_MOBILE.equalsIgnoreCase(channelId)) return orderMap.get("payParams");
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
        for (int i = 0;i < paramArray.length; i++) {
            String key = paramArray[i];
            String v = request.getParameter(key);
            if (v != null) {
                responseMap.put(key, v);
            }
        }
        return responseMap;
    }

    public boolean verifyPayResponse(Map<String,Object> map) {
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
        if(goodsOrder == null) {
            log.warn("业务订单不存在,payOrderId={},mchOrderNo={}", payOrderId, mchOrderNo);
            return false;
        }
        // 核对金额
        if(goodsOrder.getAmount() != Long.parseLong(amount)) {
            log.warn("支付金额不一致,dbPayPrice={},payPrice={}", goodsOrder.getAmount(), amount);
            return false;
        }
        return true;
    }

    public boolean verifySign(Map<String, Object> map) {
        String mchId = (String) map.get("mchId");
        if(!this.MCHID.equals(mchId)) return false;
        String localSign = PayDigestUtil.getSign(map, resKey, "sign");
        String sign = (String) map.get("sign");
        return localSign.equalsIgnoreCase(sign);
    }

}