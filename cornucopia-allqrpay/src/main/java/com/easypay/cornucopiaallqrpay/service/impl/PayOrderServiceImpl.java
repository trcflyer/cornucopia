package com.easypay.cornucopiaallqrpay.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.easypay.cornucopiaallqrpay.bean.PayOrderQueryBean;
import com.easypay.cornucopiaallqrpay.dal.dao.impl.TPayOrderMapperImpl;
import com.easypay.cornucopiaallqrpay.dal.pojo.TPayOrder;
import com.easypay.cornucopiaallqrpay.service.*;
import com.easypay.cornucopiaallqrpay.util.MyChannelUtil;
import com.easypay.cornucopiacommon.constant.PayConstant;
import com.easypay.cornucopiacommon.domain.BaseParam;
import com.easypay.cornucopiacommon.enums.RetEnum;
import com.easypay.cornucopiacommon.utils.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @Autowired
    private TPayOrderMapperImpl tPayOrderMapper;

    public int createPayOrder(JSONObject payOrder) {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("payOrder", payOrder);
        String jsonParam = RpcUtil.createBaseParam(paramMap);
        Map<String, Object> result = createPayOrder(jsonParam);
        String s = RpcUtil.mkRet(result);
        if(s == null) return 0;
        return Integer.parseInt(s);
    }

    public JSONObject queryPayOrder(String mchId,String deviceSn, String payOrderId, String mchOrderId, String executeNotify) {
        Map<String,Object> paramMap = new HashMap<>();
        Map<String, Object> result;
        if(StringUtils.isNotBlank(payOrderId)) {
            paramMap.put("mchId", mchId);
            paramMap.put("deviceSn", deviceSn);
            paramMap.put("payOrderId", payOrderId);
            String jsonParam = RpcUtil.createBaseParam(paramMap);
            result = selectPayOrderByMchIdAndPayOrderId(jsonParam);
        }else {
            paramMap.put("mchId", mchId);
            paramMap.put("deviceSn", deviceSn);
            paramMap.put("mchOrderId", mchOrderId);
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
    public JSONObject queryPayOrderStatistics(String mchId, String deviceSn,String startDate,String endDate){
        try {
            JSONObject jsonObjectRe = new JSONObject();
            PayOrderQueryBean query = new PayOrderQueryBean();
            query.setMchId(mchId);
            query.setDevice("BAR");
            query.setDeviceSn(deviceSn);
            query.setStartDate(startDate);
            query.setEndDate(endDate);

            Long totalAmt = tPayOrderMapper.selectMchTotalAmt(query);
            Long totalNum =  tPayOrderMapper.selectMchTotalNum(query);
            jsonObjectRe.put("totalAmt",AmountUtil.convertCent2Dollar(String.valueOf(totalAmt)));
            jsonObjectRe.put("totalNum",String.valueOf(totalNum));

            query.setParam1("bar_code");//支付宝条码
            Long barCodeTalAmt = tPayOrderMapper.selectMchTotalAmt(query);
            Long barCodeTotalNum =  tPayOrderMapper.selectMchTotalNum(query);
            jsonObjectRe.put("barCodeTalAmt",AmountUtil.convertCent2Dollar(String.valueOf(barCodeTalAmt)));
            jsonObjectRe.put("barCodeTotalNum",String.valueOf(barCodeTotalNum));

            query.setParam1("security_code");//支付宝刷脸付
            Long securityCodeTalAmt = tPayOrderMapper.selectMchTotalAmt(query);
            Long securityTotalNum =  tPayOrderMapper.selectMchTotalNum(query);
            jsonObjectRe.put("securityCodeTalAmt",AmountUtil.convertCent2Dollar(String.valueOf(securityCodeTalAmt)));
            jsonObjectRe.put("securityTotalNum",String.valueOf(securityTotalNum));

            query.setParam1("bar_code_w");//微信条码
            Long barCodeWTalAmt = tPayOrderMapper.selectMchTotalAmt(query);
            Long barCodeWTotalNum =  tPayOrderMapper.selectMchTotalNum(query);
            jsonObjectRe.put("barCodeWTalAmt",AmountUtil.convertCent2Dollar(String.valueOf(barCodeWTalAmt)));
            jsonObjectRe.put("barCodeWTotalNum",String.valueOf(barCodeWTotalNum));

            return jsonObjectRe;
        } catch (Exception e) {
            log.error("查询订单列表失败!原因是:{}",e);
        }
        return null;
    }
    public JSONObject queryPayOrderList(String mchId,String deviceSn,String pageNum,String pageSize,String startDate,String endDate, String scene){
        try {
            JSONObject jsonObjectRe = new JSONObject();
            Page<TPayOrder> page = PageHelper.startPage(Integer.valueOf(pageNum), Integer.valueOf(pageSize ));

            PayOrderQueryBean query = new PayOrderQueryBean();
            query.setMchId(mchId);
            query.setDevice("BAR");
            query.setDeviceSn(deviceSn);
            query.setStartDate(startDate);
            query.setEndDate(endDate);
            query.setParam1(scene);
            List<TPayOrder> list = tPayOrderMapper.selectMchOrderIdList(query);
            Long amt = tPayOrderMapper.selectMchTotalAmt(query);
            log.info("总共有:{}条数据,实际返回:{}两条数据!",page.getTotal(),list.size());
            JSONArray jsonArray = new JSONArray();
            for (TPayOrder tPayOrder : page.getResult()) {
                JSONObject  jsonObject = new JSONObject();
                jsonObject.put("payOrderId",tPayOrder.getPayOrderId());
                jsonObject.put("ordStatus",tPayOrder.getStatus());
                jsonObject.put("mchId",tPayOrder.getMchId());
                jsonObject.put("mchOrderId",tPayOrder.getMchOrderId());
                jsonObject.put("ordAmt",AmountUtil.convertCent2Dollar(String.valueOf(tPayOrder.getAmount())));
                jsonObject.put("deviceSn",tPayOrder.getDeviceSn());
                jsonObject.put("scene", MyChannelUtil.getSceneName(tPayOrder.getParam1()));
                jsonObject.put("transDate",tPayOrder.getTransDate());
                jsonObject.put("transTime", DateUtil.format(tPayOrder.getCreatetime(), DatePattern.NORM_DATETIME_PATTERN));
                jsonArray.add(jsonObject);
            }
            jsonObjectRe.put("orderList",jsonArray.toJSONString());
            jsonObjectRe.put("totalAmt",AmountUtil.convertCent2Dollar(String.valueOf(amt)));
            jsonObjectRe.put("totalNum",String.valueOf(page.getTotal()));
            return jsonObjectRe;
        } catch (Exception e) {
            log.error("查询订单列表失败!原因是:{}",e);
        }
        return null;
    }

    public String doWxPayReq(String tradeType, JSONObject payOrder, String resKey) {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("tradeType", tradeType);
        paramMap.put("payOrder", payOrder);
        String jsonParam = RpcUtil.createBaseParam(paramMap);
        Map<String, Object> result= null;
        if(tradeType.equals(PayConstant.WxConstant.TRADE_TYPE_BAR_CODE)){
            result = payChannel4WxService.doWxPayBarCodeReq(jsonParam);
        }else {
            result = payChannel4WxService.doWxPayReq(jsonParam);
        }
        String s = RpcUtil.mkRet(result);
        if(s == null) {
            Map<String, Object> map =XXPayUtil.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "", PayConstant.RETURN_VALUE_FAIL, "0111", "调用微信支付失败");
            map.put("payUrl",   result.get("payUrl") );
            return XXPayUtil.makeRetData(map, resKey);
        }
        Map<String, Object> map = XXPayUtil.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "", PayConstant.RETURN_VALUE_SUCCESS, null);
        map.putAll((Map) result.get("bizResult"));
        map.put("payUrl",   result.get("payUrl") );
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
        String deviceSn = baseParam.isNullValue("deviceSn") ? null : bizParamMap.get("deviceSn").toString();
        if (ObjectValidUtil.isInvalid(mchId, payOrderId)) {
            log.warn("根据商户号和支付订单号查询支付订单失败, {}. jsonParam={}", RetEnum.RET_PARAM_INVALID.getMessage(), jsonParam);
            return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_INVALID);
        }
        TPayOrder payOrder = super.baseSelectPayOrderByMchIdAndPayOrderId(mchId,deviceSn, payOrderId);
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
        String mchOrderId = baseParam.isNullValue("mchOrderId") ? null : bizParamMap.get("mchOrderId").toString();
        String deviceSn = baseParam.isNullValue("deviceSn") ? null : bizParamMap.get("deviceSn").toString();
        if (ObjectValidUtil.isInvalid(mchId, mchOrderId,deviceSn)) {
            log.warn("根据商户号和商户订单号查询支付订单失败, {}. jsonParam={}", RetEnum.RET_PARAM_INVALID.getMessage(), jsonParam);
            return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_INVALID);
        }
        TPayOrder payOrder = super.baseSelectPayOrderByMchIdAndMchOrderNo(mchId,deviceSn, mchOrderId);
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
