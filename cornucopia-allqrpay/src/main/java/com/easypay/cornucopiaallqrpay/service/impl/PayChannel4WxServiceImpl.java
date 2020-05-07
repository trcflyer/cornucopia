package com.easypay.cornucopiaallqrpay.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.easypay.cornucopiaallqrpay.dal.dao.impl.TPayOrderMapperImpl;
import com.easypay.cornucopiaallqrpay.dal.pojo.TPayChannel;
import com.easypay.cornucopiaallqrpay.dal.pojo.TPayOrder;
import com.easypay.cornucopiaallqrpay.service.BaseService;
import com.easypay.cornucopiaallqrpay.service.IPayChannel4WxService;
import com.easypay.cornucopiaallqrpay.service.channel.wechat.WxPayProperties;
import com.easypay.cornucopiaallqrpay.service.channel.wechat.WxPayUtil;
import com.easypay.cornucopiacommon.constant.PayConstant;
import com.easypay.cornucopiacommon.domain.BaseParam;
import com.easypay.cornucopiacommon.enums.RetEnum;
import com.easypay.cornucopiacommon.utils.BeanConvertUtils;
import com.easypay.cornucopiacommon.utils.JsonUtil;
import com.easypay.cornucopiacommon.utils.ObjectValidUtil;
import com.easypay.cornucopiacommon.utils.RpcUtil;
import com.github.binarywang.wxpay.bean.request.WxPayMicropayRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayMicropayResult;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.github.binarywang.wxpay.util.SignUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 支付渠道接口:微信
 * @author dingzhiwei jmdhappy@126.com
 * @date 2017-09-05
 * @version V1.0
 * @Copyright: www.xxpay.org
 */
@Slf4j
@Service
public class PayChannel4WxServiceImpl extends BaseService implements IPayChannel4WxService {
    @Autowired
    private TPayOrderMapperImpl tPayOrderMapper;

    @Resource
    private WxPayProperties wxPayProperties;

    public Map doWxPayReq(String jsonParam) {
        String logPrefix = "【微信支付统一下单】";
        BaseParam baseParam = JsonUtil.getObjectFromJson(jsonParam, BaseParam.class);
        Map<String, Object> bizParamMap = baseParam.getBizParamMap();
        try{
            if (ObjectValidUtil.isInvalid(bizParamMap)) {
                log.warn("{}失败, {}. jsonParam={}", logPrefix, RetEnum.RET_PARAM_NOT_FOUND.getMessage(), jsonParam);
                return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_NOT_FOUND);
            }
            JSONObject payOrderObj = baseParam.isNullValue("payOrder") ? null : JSONObject.parseObject(bizParamMap.get("payOrder").toString());
            String tradeType = baseParam.isNullValue("tradeType") ? null : bizParamMap.get("tradeType").toString();
            TPayOrder payOrder = BeanConvertUtils.map2Bean(payOrderObj, TPayOrder.class);
            if (ObjectValidUtil.isInvalid(payOrder, tradeType)) {
                log.warn("{}失败, {}. jsonParam={}", logPrefix, RetEnum.RET_PARAM_INVALID.getMessage(), jsonParam);
                return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_INVALID);
            }
            String mchId = payOrder.getMchId();
            String channelId = payOrder.getChannelId();
            TPayChannel payChannel = super.baseSelectPayChannel(mchId, channelId);
            WxPayConfig wxPayConfig = WxPayUtil.getWxPayConfig(payChannel.getParam(), tradeType, wxPayProperties.getCertRootPath(), wxPayProperties.getNotifyUrl());
            WxPayService wxPayService = new WxPayServiceImpl();
            wxPayService.setConfig(wxPayConfig);
            WxPayUnifiedOrderRequest wxPayUnifiedOrderRequest = buildUnifiedOrderRequest(payOrder, wxPayConfig);
            String payOrderId = payOrder.getPayOrderId();
            WxPayUnifiedOrderResult wxPayUnifiedOrderResult;
            try {
                wxPayUnifiedOrderResult = wxPayService.unifiedOrder(wxPayUnifiedOrderRequest);
                log.info("{} >>> 下单成功", logPrefix);
                Map<String, Object> map = new HashMap<>();
                map.put("payOrderId", payOrderId);
                map.put("prepayId", wxPayUnifiedOrderResult.getPrepayId());
                int result = super.baseUpdateStatus4Ing(payOrderId, wxPayUnifiedOrderResult.getPrepayId());
                log.info("更新支付订单号:payOrderId={},prepayId={},result={}", payOrderId, wxPayUnifiedOrderResult.getPrepayId(), result);
                switch (tradeType) {
                    case PayConstant.WxConstant.TRADE_TYPE_NATIVE : {
                        map.put("codeUrl", wxPayUnifiedOrderResult.getCodeURL());   // 二维码支付链接
                        break;
                    }
                    case PayConstant.WxConstant.TRADE_TYPE_APP : {
                        Map<String, String> payInfo = new HashMap<>();
                        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
                        String nonceStr = String.valueOf(System.currentTimeMillis());
                        // APP支付绑定的是微信开放平台上的账号，APPID为开放平台上绑定APP后发放的参数
                        String appId = wxPayConfig.getAppId();
                        Map<String, String> configMap = new HashMap<>();
                        // 此map用于参与调起sdk支付的二次签名,格式全小写，timestamp只能是10位,格式固定，切勿修改
                        String partnerId = wxPayConfig.getMchId();
                        configMap.put("prepayid", wxPayUnifiedOrderResult.getPrepayId());
                        configMap.put("partnerid", partnerId);
                        String packageValue = "Sign=WXPay";
                        configMap.put("package", packageValue);
                        configMap.put("timestamp", timestamp);
                        configMap.put("noncestr", nonceStr);
                        configMap.put("appid", appId);
                        // 此map用于客户端与微信服务器交互
                        payInfo.put("sign", SignUtils.createSign(configMap, wxPayConfig.getMchKey(), null));
                        payInfo.put("prepayid", wxPayUnifiedOrderResult.getPrepayId());
                        payInfo.put("partnerid", partnerId);
                        payInfo.put("appid", appId);
                        payInfo.put("package", packageValue);
                        payInfo.put("timestamp", timestamp);
                        payInfo.put("noncestr", nonceStr);
                        map.put("payParams", payInfo);
                        break;
                    }
                    case PayConstant.WxConstant.TRADE_TYPE_JSPAI : {
                        Map<String, String> payInfo = new HashMap<>();
                        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
                        String nonceStr = String.valueOf(System.currentTimeMillis());
                        payInfo.put("appId", wxPayUnifiedOrderResult.getAppid());
                        // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
                        payInfo.put("timeStamp", timestamp);
                        payInfo.put("nonceStr", nonceStr);
                        payInfo.put("package", "prepay_id=" + wxPayUnifiedOrderResult.getPrepayId());
                        payInfo.put("signType", WxPayConstants.SignType.MD5);
                        payInfo.put("paySign", SignUtils.createSign(payInfo, wxPayConfig.getMchKey(), null));
                        map.put("payParams", payInfo);
                        break;
                    }
                    case PayConstant.WxConstant.TRADE_TYPE_MWEB : {
                        map.put("payUrl", wxPayUnifiedOrderResult.getMwebUrl());    // h5支付链接地址
                        break;
                    }
                }
                return RpcUtil.createBizResult(baseParam, map);
            } catch (WxPayException e) {
                log.error(e.getMessage(), "下单失败");
                //出现业务错误
                log.info("{}下单返回失败", logPrefix);
                log.info("err_code:{}", e.getErrCode());
                log.info("err_code_des:{}", e.getErrCodeDes());

                return RpcUtil.createFailResult(baseParam, RetEnum.RET_BIZ_WX_PAY_CREATE_FAIL);

               // return XXPayUtil.makeRetData(XXPayUtil.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "", PayConstant.RETURN_VALUE_FAIL, "0111", "调用微信支付失败," + e.getErrCode() + ":" + e.getErrCodeDes()), resKey);
            }
        }catch (Exception e) {
            log.error(e.getMessage(), "微信支付统一下单异常");
            return RpcUtil.createFailResult(baseParam, RetEnum.RET_BIZ_WX_PAY_CREATE_FAIL);

            //return XXPayUtil.makeRetFail(XXPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "", PayConstant.RETURN_VALUE_FAIL, PayEnum.ERR_0001));
        }
    }

    /**
     * 构建微信统一下单请求数据
     * @param payOrder
     * @param wxPayConfig
     * @return
     */
    WxPayUnifiedOrderRequest buildUnifiedOrderRequest(TPayOrder payOrder, WxPayConfig wxPayConfig) {
        String tradeType = wxPayConfig.getTradeType();
        String payOrderId = payOrder.getPayOrderId();
        Integer totalFee = payOrder.getAmount().intValue();// 支付金额,单位分
        String deviceInfo = payOrder.getDevice();
        String body = payOrder.getBody();
        String detail = null;
        String attach = null;
        String outTradeNo = payOrderId;
        String feeType = "CNY";
        String spBillCreateIP = payOrder.getClientIp();
        String timeStart = null;
        String timeExpire = null;
        String goodsTag = null;
        String notifyUrl = wxPayConfig.getNotifyUrl();
        String productId = null;
        if(tradeType.equals(PayConstant.WxConstant.TRADE_TYPE_NATIVE)) productId = JSON.parseObject(payOrder.getExtra()).getString("productId");
        String limitPay = null;
        String openId = null;
        if(tradeType.equals(PayConstant.WxConstant.TRADE_TYPE_JSPAI)) openId = JSON.parseObject(payOrder.getExtra()).getString("openId");
        String sceneInfo = null;
        if(tradeType.equals(PayConstant.WxConstant.TRADE_TYPE_MWEB)) sceneInfo = JSON.parseObject(payOrder.getExtra()).getString("sceneInfo");
        // 微信统一下单请求对象
        WxPayUnifiedOrderRequest request = new WxPayUnifiedOrderRequest();
        request.setDeviceInfo(deviceInfo);
        request.setBody(body);
        request.setDetail(detail);
        request.setAttach(attach);
        request.setOutTradeNo(outTradeNo);
        request.setFeeType(feeType);
        request.setTotalFee(totalFee);
        request.setSpbillCreateIp(spBillCreateIP);
        request.setTimeStart(timeStart);
        request.setTimeExpire(timeExpire);
        request.setGoodsTag(goodsTag);
        request.setNotifyURL(notifyUrl);
        request.setTradeType(tradeType);
        request.setProductId(productId);
        request.setLimitPay(limitPay);
        request.setOpenid(openId);
        request.setSceneInfo(sceneInfo);

        return request;
    }

    public Map doWxPayBarCodeReq(String jsonParam){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String logPrefix = "【微信支付付款码支付】";
        BaseParam baseParam = JsonUtil.getObjectFromJson(jsonParam, BaseParam.class);
        Map<String, Object> bizParamMap = baseParam.getBizParamMap();
        TPayOrder payOrder = null;
        String  payUrl = null;
        try{
            if (ObjectValidUtil.isInvalid(bizParamMap)) {
                log.warn("{}失败, {}. jsonParam={}", logPrefix, RetEnum.RET_PARAM_NOT_FOUND.getMessage(), jsonParam);
                return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_NOT_FOUND);
            }
            JSONObject payOrderObj = baseParam.isNullValue("payOrder") ? null : JSONObject.parseObject(bizParamMap.get("payOrder").toString());
            String tradeType = baseParam.isNullValue("tradeType") ? null : bizParamMap.get("tradeType").toString();
            payOrder = BeanConvertUtils.map2Bean(payOrderObj, TPayOrder.class);
            if (ObjectValidUtil.isInvalid(payOrder, tradeType)) {
                log.warn("{}失败, {}. jsonParam={}", logPrefix, RetEnum.RET_PARAM_INVALID.getMessage(), jsonParam);
                return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_INVALID);
            }
            String mchId = payOrder.getMchId();
            String channelId = payOrder.getChannelId();
            TPayChannel payChannel = super.baseSelectPayChannel(mchId, channelId);
            WxPayConfig wxPayConfig = WxPayUtil.getWxPayConfig(payChannel.getParam(), tradeType, wxPayProperties.getCertRootPath(), wxPayProperties.getNotifyUrl());
            WxPayService wxPayService = new WxPayServiceImpl();
            wxPayService.setConfig(wxPayConfig);
            WxPayMicropayRequest wxPayUnifiedOrderRequest = buildWxPayMicropayRequest(payOrder, wxPayConfig);
            String payOrderId = payOrder.getPayOrderId();
            WxPayMicropayResult wxPayMicropayResult;
            try {
                wxPayMicropayResult = wxPayService.micropay(wxPayUnifiedOrderRequest);
                log.info("{} >>> 下单成功", logPrefix);
                Map<String, Object> map = new HashMap<>();
                map.put("payOrderId", payOrderId);
                map.put("prepayId", wxPayMicropayResult.getTransactionId());
                int result = super.baseUpdateStatus4Ing(payOrderId, wxPayMicropayResult.getTransactionId());
                log.info("更新支付订单号:payOrderId={},微信订单号transaction_id={},result={}", payOrderId, wxPayMicropayResult.getTransactionId(), result);

                Map<String, String> payInfo = new HashMap<>();
                String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
                String nonceStr = String.valueOf(System.currentTimeMillis());
                payInfo.put("appId", wxPayMicropayResult.getAppid());
                // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
                payInfo.put("timeStamp", timestamp);
                payInfo.put("nonceStr", nonceStr);
                payInfo.put("package", "prepay_id=" + wxPayMicropayResult.getTransactionId());
                payInfo.put("signType", WxPayConstants.SignType.MD5);
                payInfo.put("paySign", SignUtils.createSign(payInfo, wxPayConfig.getMchKey(), null));
                map.put("payParams", payInfo);

                resultMap = RpcUtil.createBizResult(baseParam, map);

                Map<String, Object> mapTemp = new HashMap<>();
                mapTemp.putAll((Map) resultMap.get("bizResult"));
                log.info("支付结果,resultMap:{}",resultMap);
                 payUrl =  dealResult(payOrder.getPayOrderId(),"成功","SUCCESS",String.valueOf(mapTemp.get("prepayId")));

            } catch (WxPayException e) {
                log.error(e.getMessage(), "下单失败");
                //出现业务错误
                log.info("{}下单返回失败", logPrefix);
                log.info("err_code:{}", e.getErrCode());
                log.info("err_code_des:{}", e.getErrCodeDes());

                resultMap = RpcUtil.createFailResult(baseParam, RetEnum.RET_BIZ_WX_PAY_CREATE_FAIL);
                 payUrl =  dealResult(payOrder.getPayOrderId(),e.getErrCodeDes(),e.getErrCode(),null);
            }
        }catch (Exception e) {
            log.error(e.getMessage(), "微信支付付款码支付异常");
            resultMap = RpcUtil.createFailResult(baseParam, RetEnum.RET_BIZ_WX_PAY_CREATE_FAIL);
             payUrl = dealResult(payOrder.getPayOrderId(),"下单异常","FAIL",null);

        }
        resultMap.put("payUrl",   payUrl );
        return resultMap;
    }
    private String dealResult(String payOrderId,String respMsg,String result_code,String out_trade_no){
        JSONObject result = new JSONObject();

        TPayOrder tPayOrder = new TPayOrder();
        tPayOrder.setPayOrderId(payOrderId);
        tPayOrder.setRespCode(result_code);
        tPayOrder.setRespMsg(respMsg);
        tPayOrder.setChannelorderno(out_trade_no);

        if("SUCCESS".equals(result_code)){
            log.info("下单结果成功");
            result.put("ordStatus","2");
            tPayOrder.setStatus((byte)2);
        }if("USERPAYING".equals(result_code)){
            log.info("需要用户输入支付密码");
            result.put("ordStatus","1");
            tPayOrder.setStatus((byte)1);
        }else {
            log.info("下单结果失败");
            result.put("ordStatus","9");
            tPayOrder.setStatus((byte) 9);
        }
        result.put("payOrderId",payOrderId);
        result.put("respMsg",respMsg);
        tPayOrderMapper.updateRespByPayOrderId(tPayOrder);
        return result.toJSONString();
    }
    /**
     * 构建微信统一下单请求数据
     * @param payOrder
     * @param wxPayConfig
     * @return
     */
    WxPayMicropayRequest buildWxPayMicropayRequest(TPayOrder payOrder, WxPayConfig wxPayConfig) {
        String payOrderId = payOrder.getPayOrderId();
        Integer totalFee = payOrder.getAmount().intValue();// 支付金额,单位分
        String body = payOrder.getBody();
        String detail = null;
        String attach = null;
        String outTradeNo = payOrderId;
        String feeType = "CNY";
        String spBillCreateIP = payOrder.getClientIp();
        String goodsTag = null;
        String limitPay = null;
        // 微信付款码支付请求对象
        WxPayMicropayRequest request =  WxPayMicropayRequest.newBuilder().build();

        request.setBody(body);
        request.setDetail(detail);
        request.setAttach(attach);
        request.setOutTradeNo(outTradeNo);
        request.setFeeType(feeType);
        request.setTotalFee(totalFee);
        request.setSpbillCreateIp(spBillCreateIP);
        request.setGoodsTag(goodsTag);
        request.setAuthCode(payOrder.getParam2());
        request.setLimitPay(limitPay);

        return request;
    }
}
