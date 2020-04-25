package com.easypay.cornucopiaallqrpay.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.easypay.cornucopiaallqrpay.dal.pojo.TPayOrder;
import com.easypay.cornucopiaallqrpay.service.*;
import com.easypay.cornucopiacommon.constant.PayConstant;
import com.easypay.cornucopiacommon.domain.BaseParam;
import com.easypay.cornucopiacommon.enums.RetEnum;
import com.easypay.cornucopiacommon.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: dingzhiwei
 * @date: 17/9/8
 * @description:
 */
@Service
@Slf4j
public class PayOrderServiceImpl extends BaseService implements IPayOrderService {


    @Autowired
    private INotifyPayService notifyPayService;

    @Autowired
    private IPayChannel4WxService payChannel4WxService;

    @Autowired
    private IPayChannel4AliService payChannel4AliService;

    public int createPayOrder(JSONObject payOrder) {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("payOrder", payOrder);
        String jsonParam = RpcUtil.createBaseParam(paramMap);
        Map<String, Object> result = createPayOrder(jsonParam);
        String s = RpcUtil.mkRet(result);
        if(s == null) return 0;
        return Integer.parseInt(s);
    }

    public JSONObject queryPayOrder(String mchId, String payOrderId, String mchOrderNo, String executeNotify) {
        Map<String,Object> paramMap = new HashMap<>();
        Map<String, Object> result;
        if(StringUtils.isNotBlank(payOrderId)) {
            paramMap.put("mchId", mchId);
            paramMap.put("payOrderId", payOrderId);
            String jsonParam = RpcUtil.createBaseParam(paramMap);
            result = selectPayOrderByMchIdAndPayOrderId(jsonParam);
        }else {
            paramMap.put("mchId", mchId);
            paramMap.put("mchOrderNo", mchOrderNo);
            String jsonParam = RpcUtil.createBaseParam(paramMap);
            result = selectPayOrderByMchIdAndMchOrderNo(jsonParam);
        }
        String s = RpcUtil.mkRet(result);
        if(s == null) return null;
        boolean isNotify = Boolean.parseBoolean(executeNotify);
        JSONObject payOrder = JSONObject.parseObject(s);
        if(isNotify) {
            paramMap = new HashMap<>();
            paramMap.put("payOrderId", payOrderId);
            String jsonParam = RpcUtil.createBaseParam(paramMap);
            result = notifyPayService.sendBizPayNotify(jsonParam);
            s = RpcUtil.mkRet(result);
            log.info("业务查单完成,并再次发送业务支付通知.发送结果:{}", s);
        }
        return payOrder;
    }

    public String doWxPayReq(String tradeType, JSONObject payOrder, String resKey) {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("tradeType", tradeType);
        paramMap.put("payOrder", payOrder);
        String jsonParam = RpcUtil.createBaseParam(paramMap);
        Map<String, Object> result= null;
        if(tradeType.equals(PayConstant.PAY_CHANNEL_WX_BAR_CODE)){
            result = payChannel4WxService.doWxPayBarCodeReq(jsonParam);
        }else {
            result = payChannel4WxService.doWxPayReq(jsonParam);
        }
        String s = RpcUtil.mkRet(result);
        if(s == null) {
            return XXPayUtil.makeRetData(XXPayUtil.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "", PayConstant.RETURN_VALUE_FAIL, "0111", "调用微信支付失败"), resKey);
        }
        Map<String, Object> map = XXPayUtil.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "", PayConstant.RETURN_VALUE_SUCCESS, null);
        map.putAll((Map) result.get("bizResult"));
        return XXPayUtil.makeRetData(map, resKey);
    }

    public String doAliPayReq(String channelId, JSONObject payOrder, String resKey) {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("payOrder", payOrder);
        String jsonParam = RpcUtil.createBaseParam(paramMap);
        Map<String, Object> result;
        switch (channelId) {
            case PayConstant.PAY_CHANNEL_ALIPAY_MOBILE :
                result = payChannel4AliService.doAliPayMobileReq(jsonParam);
                break;
            case PayConstant.PAY_CHANNEL_ALIPAY_PC :
                result = payChannel4AliService.doAliPayPcReq(jsonParam);
                break;
            case PayConstant.PAY_CHANNEL_ALIPAY_WAP :
                result = payChannel4AliService.doAliPayWapReq(jsonParam);
                break;
            case PayConstant.PAY_CHANNEL_ALIPAY_QR :
                result = payChannel4AliService.doAliPayQrReq(jsonParam);
                break;
            case PayConstant.PAY_CHANNEL_ALIPAY_BAR_CODE:
                result = payChannel4AliService.doAliPayBarCodeReq(jsonParam);
                break;
            default:
                result = null;
                break;
        }
        String s = RpcUtil.mkRet(result);
        if(s == null) {
            return XXPayUtil.makeRetData(XXPayUtil.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "", PayConstant.RETURN_VALUE_FAIL, "0111", "调用支付宝支付失败"), resKey);
        }
        Map<String, Object> map = XXPayUtil.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "", PayConstant.RETURN_VALUE_SUCCESS, null);
        map.putAll((Map) result.get("bizResult"));
        return XXPayUtil.makeRetData(map, resKey);
    }

    @Override
    public Map createPayOrder(String jsonParam) {
        BaseParam baseParam = JsonUtil.getObjectFromJson(jsonParam, BaseParam.class);
        Map<String, Object> bizParamMap = baseParam.getBizParamMap();
        if (ObjectValidUtil.isInvalid(bizParamMap)) {
            log.warn("新增支付订单失败, {}. jsonParam={}", RetEnum.RET_PARAM_NOT_FOUND.getMessage(), jsonParam);
            return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_NOT_FOUND);
        }
        JSONObject payOrderObj = baseParam.isNullValue("payOrder") ? null : JSONObject.parseObject(bizParamMap.get("payOrder").toString());
        if(payOrderObj == null) {
            log.warn("新增支付订单失败, {}. jsonParam={}", RetEnum.RET_PARAM_INVALID.getMessage(), jsonParam);
            return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_INVALID);
        }
        TPayOrder payOrder = BeanConvertUtils.map2Bean(payOrderObj, TPayOrder.class);
        if(payOrder == null) {
            log.warn("新增支付订单失败, {}. jsonParam={}", RetEnum.RET_PARAM_INVALID.getMessage(), jsonParam);
            return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_INVALID);
        }
        int result = super.baseCreatePayOrder(payOrder);
        return RpcUtil.createBizResult(baseParam, result);
    }

    @Override
    public Map selectPayOrder(String jsonParam) {
        BaseParam baseParam = JsonUtil.getObjectFromJson(jsonParam, BaseParam.class);
        Map<String, Object> bizParamMap = baseParam.getBizParamMap();
        if (ObjectValidUtil.isInvalid(bizParamMap)) {
            log.warn("根据支付订单号查询支付订单失败, {}. jsonParam={}", RetEnum.RET_PARAM_NOT_FOUND.getMessage(), jsonParam);
            return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_NOT_FOUND);
        }
        String payOrderId = baseParam.isNullValue("payOrderId") ? null : bizParamMap.get("payOrderId").toString();
        if (ObjectValidUtil.isInvalid(payOrderId)) {
            log.warn("根据支付订单号查询支付订单失败, {}. jsonParam={}", RetEnum.RET_PARAM_INVALID.getMessage(), jsonParam);
            return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_INVALID);
        }
        TPayOrder payOrder = super.baseSelectPayOrder(payOrderId);
        if(payOrder == null) return RpcUtil.createFailResult(baseParam, RetEnum.RET_BIZ_DATA_NOT_EXISTS);
        String jsonResult = JsonUtil.object2Json(payOrder);
        return RpcUtil.createBizResult(baseParam, jsonResult);
    }

    @Override
    public Map selectPayOrderByMchIdAndPayOrderId(String jsonParam) {
        BaseParam baseParam = JsonUtil.getObjectFromJson(jsonParam, BaseParam.class);
        Map<String, Object> bizParamMap = baseParam.getBizParamMap();
        if (ObjectValidUtil.isInvalid(bizParamMap)) {
            log.warn("根据商户号和支付订单号查询支付订单失败, {}. jsonParam={}", RetEnum.RET_PARAM_NOT_FOUND.getMessage(), jsonParam);
            return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_NOT_FOUND);
        }
        String mchId = baseParam.isNullValue("mchId") ? null : bizParamMap.get("mchId").toString();
        String payOrderId = baseParam.isNullValue("payOrderId") ? null : bizParamMap.get("payOrderId").toString();
        if (ObjectValidUtil.isInvalid(mchId, payOrderId)) {
            log.warn("根据商户号和支付订单号查询支付订单失败, {}. jsonParam={}", RetEnum.RET_PARAM_INVALID.getMessage(), jsonParam);
            return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_INVALID);
        }
        TPayOrder payOrder = super.baseSelectPayOrderByMchIdAndPayOrderId(mchId, payOrderId);
        if(payOrder == null) return RpcUtil.createFailResult(baseParam, RetEnum.RET_BIZ_DATA_NOT_EXISTS);
        String jsonResult = JsonUtil.object2Json(payOrder);
        return RpcUtil.createBizResult(baseParam, jsonResult);
    }

    @Override
    public Map selectPayOrderByMchIdAndMchOrderNo(String jsonParam) {
        BaseParam baseParam = JsonUtil.getObjectFromJson(jsonParam, BaseParam.class);
        Map<String, Object> bizParamMap = baseParam.getBizParamMap();
        if (ObjectValidUtil.isInvalid(bizParamMap)) {
            log.warn("根据商户号和商户订单号查询支付订单失败, {}. jsonParam={}", RetEnum.RET_PARAM_NOT_FOUND.getMessage(), jsonParam);
            return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_NOT_FOUND);
        }
        String mchId = baseParam.isNullValue("mchId") ? null : bizParamMap.get("mchId").toString();
        String mchOrderNo = baseParam.isNullValue("mchOrderNo") ? null : bizParamMap.get("mchOrderNo").toString();
        if (ObjectValidUtil.isInvalid(mchId, mchOrderNo)) {
            log.warn("根据商户号和商户订单号查询支付订单失败, {}. jsonParam={}", RetEnum.RET_PARAM_INVALID.getMessage(), jsonParam);
            return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_INVALID);
        }
        TPayOrder payOrder = super.baseSelectPayOrderByMchIdAndMchOrderNo(mchId, mchOrderNo);
        if(payOrder == null) return RpcUtil.createFailResult(baseParam, RetEnum.RET_BIZ_DATA_NOT_EXISTS);
        String jsonResult = JsonUtil.object2Json(payOrder);
        return RpcUtil.createBizResult(baseParam, jsonResult);
    }

    @Override
    public Map updateStatus4Ing(String jsonParam) {
        BaseParam baseParam = JsonUtil.getObjectFromJson(jsonParam, BaseParam.class);
        Map<String, Object> bizParamMap = baseParam.getBizParamMap();
        if (ObjectValidUtil.isInvalid(bizParamMap)) {
            log.warn("修改支付订单状态为支付中失败, {}. jsonParam={}", RetEnum.RET_PARAM_NOT_FOUND.getMessage(), jsonParam);
            return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_NOT_FOUND);
        }
        String payOrderId = baseParam.isNullValue("payOrderId") ? null : bizParamMap.get("payOrderId").toString();
        String channelOrderNo = baseParam.isNullValue("channelOrderNo") ? null : bizParamMap.get("channelOrderNo").toString();
        if (ObjectValidUtil.isInvalid(payOrderId, channelOrderNo)) {
            log.warn("修改支付订单状态为支付中失败, {}. jsonParam={}", RetEnum.RET_PARAM_INVALID.getMessage(), jsonParam);
            return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_INVALID);
        }
        int result =  super.baseUpdateStatus4Ing(payOrderId, channelOrderNo);
        return RpcUtil.createBizResult(baseParam, result);
    }

    @Override
    public Map updateStatus4Success(String jsonParam) {
        BaseParam baseParam = JsonUtil.getObjectFromJson(jsonParam, BaseParam.class);
        Map<String, Object> bizParamMap = baseParam.getBizParamMap();
        if (ObjectValidUtil.isInvalid(bizParamMap)) {
            log.warn("修改支付订单状态为支付成功失败, {}. jsonParam={}", RetEnum.RET_PARAM_NOT_FOUND.getMessage(), jsonParam);
            return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_NOT_FOUND);
        }
        String payOrderId = baseParam.isNullValue("payOrderId") ? null : bizParamMap.get("payOrderId").toString();
        if (ObjectValidUtil.isInvalid(payOrderId)) {
            log.warn("修改支付订单状态为支付成功失败, {}. jsonParam={}", RetEnum.RET_PARAM_INVALID.getMessage(), jsonParam);
            return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_INVALID);
        }
        int result = super.baseUpdateStatus4Success(payOrderId, null);
        return RpcUtil.createBizResult(baseParam, result);
    }

    @Override
    public Map updateStatus4Complete(String jsonParam) {
        BaseParam baseParam = JsonUtil.getObjectFromJson(jsonParam, BaseParam.class);
        Map<String, Object> bizParamMap = baseParam.getBizParamMap();
        if (ObjectValidUtil.isInvalid(bizParamMap)) {
            log.warn("修改支付订单状态为支付完成失败, {}. jsonParam={}", RetEnum.RET_PARAM_NOT_FOUND.getMessage(), jsonParam);
            return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_NOT_FOUND);
        }
        String payOrderId = baseParam.isNullValue("payOrderId") ? null : bizParamMap.get("payOrderId").toString();
        if (ObjectValidUtil.isInvalid(payOrderId)) {
            log.warn("修改支付订单状态为支付完成失败, {}. jsonParam={}", RetEnum.RET_PARAM_INVALID.getMessage(), jsonParam);
            return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_INVALID);
        }
        int result =  super.baseUpdateStatus4Complete(payOrderId);
        return RpcUtil.createBizResult(baseParam, result);
    }

    @Override
    public Map updateNotify(String jsonParam) {
        Map<String, Object> map = new HashMap<>();
        log.info("无异步，不通知");
        return map;
    }
}
