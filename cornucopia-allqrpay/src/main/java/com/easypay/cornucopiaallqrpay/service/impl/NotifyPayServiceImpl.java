package com.easypay.cornucopiaallqrpay.service.impl;


import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.easypay.cornucopiaallqrpay.dal.pojo.TPayChannel;
import com.easypay.cornucopiaallqrpay.dal.pojo.TPayOrder;
import com.easypay.cornucopiaallqrpay.service.INotifyPayService;
import com.easypay.cornucopiaallqrpay.service.Notify4BasePay;
import com.easypay.cornucopiaallqrpay.service.channel.alipay.AlipayConfig;
import com.easypay.cornucopiaallqrpay.service.channel.wechat.WxPayUtil;
import com.easypay.cornucopiacommon.constant.PayConstant;
import com.easypay.cornucopiacommon.domain.BaseParam;
import com.easypay.cornucopiacommon.enums.RetEnum;
import com.easypay.cornucopiacommon.utils.JsonUtil;
import com.easypay.cornucopiacommon.utils.ObjectValidUtil;
import com.easypay.cornucopiacommon.utils.RpcUtil;
import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: dingzhiwei
 * @date: 17/9/10
 * @description:
 */
@Service
@Slf4j
public class NotifyPayServiceImpl extends Notify4BasePay implements INotifyPayService {

    @Autowired
    private AlipayConfig alipayConfig;

    @Override
    public Map doAliPayNotify(String jsonParam) {
        String logPrefix = "【处理支付宝支付回调】";
        log.info("====== 开始处理支付宝支付回调通知 ======");
        BaseParam baseParam = JsonUtil.getObjectFromJson(jsonParam, BaseParam.class);
        Map<String, Object> bizParamMap = baseParam.getBizParamMap();
        if (ObjectValidUtil.isInvalid(bizParamMap)) {
            log.warn("处理支付宝支付回调失败, {}. jsonParam={}", RetEnum.RET_PARAM_NOT_FOUND.getMessage(), jsonParam);
            return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_NOT_FOUND);
        }
        Map params = baseParam.isNullValue("params") ? null : (Map) bizParamMap.get("params");
        if (ObjectValidUtil.isInvalid(params)) {
            log.warn("处理支付宝支付回调失败, {}. jsonParam={}", RetEnum.RET_PARAM_INVALID.getMessage(), jsonParam);
            return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_INVALID);
        }
        Map<String, Object> payContext = new HashMap<>();
        TPayOrder payOrder;
        payContext.put("parameters", params);
        if(!verifyAliPayParams(payContext)) {
            return RpcUtil.createFailResult(baseParam, RetEnum.RET_BIZ_PAY_NOTIFY_VERIFY_FAIL);
        }
        log.info("{}验证支付通知数据及签名通过", logPrefix);
        String trade_status = String.valueOf(params.get("trade_status"));	// 交易状态
        String trade_no = String.valueOf(params.get("trade_no"));			// 渠道订单号
        // 支付状态成功或者完成
        if (trade_status.equals(PayConstant.AlipayConstant.TRADE_STATUS_SUCCESS) ||
                trade_status.equals(PayConstant.AlipayConstant.TRADE_STATUS_FINISHED)) {
            int updatePayOrderRows;
            payOrder = (TPayOrder)payContext.get("payOrder");
            byte payStatus = payOrder.getStatus(); // 0：订单生成，1：支付中，-1：支付失败，2：支付成功，3：业务处理完成，-2：订单过期
            if (payStatus != PayConstant.PAY_STATUS_SUCCESS && payStatus != PayConstant.PAY_STATUS_COMPLETE) {
                updatePayOrderRows = super.baseUpdateStatus4Success(payOrder.getPayorderid(), trade_no);
                if (updatePayOrderRows != 1) {
                    log.error("{}更新支付状态失败,将payOrderId={},更新payStatus={}失败", logPrefix, payOrder.getPayorderid(), PayConstant.PAY_STATUS_SUCCESS);
                    log.info("{}响应给支付宝结果：{}", logPrefix, PayConstant.RETURN_ALIPAY_VALUE_FAIL);
                    return RpcUtil.createBizResult(baseParam, PayConstant.RETURN_ALIPAY_VALUE_FAIL);
                }
                log.info("{}更新支付状态成功,将payOrderId={},更新payStatus={}成功", logPrefix, payOrder.getPayorderid(), PayConstant.PAY_STATUS_SUCCESS);
                payOrder.setStatus(PayConstant.PAY_STATUS_SUCCESS);
                payOrder.setChannelorderno(trade_no);
            }
        }else{
            // 其他状态
            log.info("{}支付状态trade_status={},不做业务处理", logPrefix, trade_status);
            log.info("{}响应给支付宝结果：{}", logPrefix, PayConstant.RETURN_ALIPAY_VALUE_SUCCESS);
            return RpcUtil.createBizResult(baseParam, PayConstant.RETURN_ALIPAY_VALUE_SUCCESS);
        }
        doNotify(payOrder);
        log.info("====== 完成处理支付宝支付回调通知 ======");
        return RpcUtil.createBizResult(baseParam, PayConstant.RETURN_ALIPAY_VALUE_SUCCESS);
    }

    @Override
    public Map doWxPayNotify(String jsonParam) {
        String logPrefix = "【处理微信支付回调】";
        log.info("====== 开始处理微信支付回调通知 ======");
        BaseParam baseParam = JsonUtil.getObjectFromJson(jsonParam, BaseParam.class);
        try {
            Map<String, Object> bizParamMap = baseParam.getBizParamMap();
            if (ObjectValidUtil.isInvalid(bizParamMap)) {
                log.warn("处理微信支付回调失败, {}. jsonParam={}", RetEnum.RET_PARAM_NOT_FOUND.getMessage(), jsonParam);
                return RpcUtil.createBizResult(baseParam, WxPayNotifyResponse.fail(RetEnum.RET_PARAM_NOT_FOUND.getMessage()));
                //return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_NOT_FOUND);
            }
            String xmlResult = baseParam.isNullValue("xmlResult") ? null : bizParamMap.get("xmlResult").toString();
            if (ObjectValidUtil.isInvalid(xmlResult)) {
                log.warn("处理微信支付回调失败, {}. jsonParam={}", RetEnum.RET_PARAM_INVALID.getMessage(), jsonParam);
                return RpcUtil.createBizResult(baseParam, WxPayNotifyResponse.fail(RetEnum.RET_PARAM_INVALID.getMessage()));
                //return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_INVALID);
            }
            WxPayService wxPayService = new WxPayServiceImpl();
            WxPayOrderNotifyResult result = WxPayOrderNotifyResult.fromXML(xmlResult);
            Map<String, Object> payContext = new HashMap<>();
            payContext.put("parameters", result);
            // 验证业务数据是否正确,验证通过后返回PayOrder和WxPayConfig对象
            if(!verifyWxPayParams(payContext)) {
                return RpcUtil.createBizResult(baseParam, WxPayNotifyResponse.fail((String) payContext.get("retMsg")));
            }
            TPayOrder payOrder = (TPayOrder) payContext.get("payOrder");
            WxPayConfig wxPayConfig = (WxPayConfig) payContext.get("wxPayConfig");
            wxPayService.setConfig(wxPayConfig);
            // 这里做了签名校验(这里又做了一次xml转换对象,可以考虑优化)
            wxPayService.parseOrderNotifyResult(xmlResult);
            // 处理订单
            byte payStatus = payOrder.getStatus(); // 0：订单生成，1：支付中，-1：支付失败，2：支付成功，3：业务处理完成，-2：订单过期
            if (payStatus != PayConstant.PAY_STATUS_SUCCESS && payStatus != PayConstant.PAY_STATUS_COMPLETE) {
                int updatePayOrderRows = super.baseUpdateStatus4Success(payOrder.getPayorderid(), result.getTransactionId());
                if (updatePayOrderRows != 1) {
                    log.error("{}更新支付状态失败,将payOrderId={},更新payStatus={}失败", logPrefix, payOrder.getPayorderid(), PayConstant.PAY_STATUS_SUCCESS);
                    return RpcUtil.createBizResult(baseParam, WxPayNotifyResponse.fail("处理订单失败"));
                }
                log.error("{}更新支付状态成功,将payOrderId={},更新payStatus={}成功", logPrefix, payOrder.getPayorderid(), PayConstant.PAY_STATUS_SUCCESS);
                payOrder.setStatus(PayConstant.PAY_STATUS_SUCCESS);
                payOrder.setChannelorderno(result.getTransactionId());
            }
            // 业务系统后端通知
            doNotify(payOrder);
            log.info("====== 完成处理微信支付回调通知 ======");
            return RpcUtil.createBizResult(baseParam, WxPayNotifyResponse.success("OK"));
        } catch (WxPayException e) {
            //出现业务错误
            log.error(e.getMessage(), "微信回调结果异常,异常原因");
            log.info("{}请求数据result_code=FAIL", logPrefix);
            log.info("err_code:", e.getErrCode());
            log.info("err_code_des:", e.getErrCodeDes());
            return RpcUtil.createBizResult(baseParam, WxPayNotifyResponse.fail(e.getMessage()));
        } catch (Exception e) {
            log.error(e.getMessage(), "微信回调结果异常,异常原因");
            return RpcUtil.createBizResult(baseParam, WxPayNotifyResponse.fail(e.getMessage()));
        }
    }

    @Override
    public Map sendBizPayNotify(String jsonParam) {
        BaseParam baseParam = JsonUtil.getObjectFromJson(jsonParam, BaseParam.class);
        Map<String, Object> bizParamMap = baseParam.getBizParamMap();
        if (ObjectValidUtil.isInvalid(bizParamMap)) {
            log.warn("发送业务支付通知失败, {}. jsonParam={}", RetEnum.RET_PARAM_NOT_FOUND.getMessage(), jsonParam);
            return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_NOT_FOUND);
        }
        String payOrderId = baseParam.isNullValue("payOrderId") ? null : bizParamMap.get("payOrderId").toString();
        if(ObjectValidUtil.isInvalid(payOrderId)) {
            log.warn("发送业务支付通知失败, {}. jsonParam={}", RetEnum.RET_PARAM_INVALID.getMessage(), jsonParam);
            return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_INVALID);
        }
        TPayOrder payOrder = super.baseSelectPayOrder(payOrderId);
        if(payOrder == null) return RpcUtil.createFailResult(baseParam, RetEnum.RET_BIZ_DATA_NOT_EXISTS);
        try {
            // 发送业务支付通知
            super.doNotify(payOrder);
        }catch (Exception e) {
            return RpcUtil.createBizResult(baseParam, 0);
        }
        return RpcUtil.createBizResult(baseParam, 1);
    }


    /**
     * 验证支付宝支付通知参数
     * @return
     */
    public boolean verifyAliPayParams(Map<String, Object> payContext) {
        Map<String,String> params = (Map<String,String>)payContext.get("parameters");
        String out_trade_no = params.get("out_trade_no");		// 商户订单号
        String total_amount = params.get("total_amount"); 		// 支付金额
        if (StringUtils.isEmpty(out_trade_no)) {
            log.error("AliPay Notify parameter out_trade_no is empty. out_trade_no={}", out_trade_no);
            payContext.put("retMsg", "out_trade_no is empty");
            return false;
        }
        if (StringUtils.isEmpty(total_amount)) {
            log.error("AliPay Notify parameter total_amount is empty. total_fee={}", total_amount);
            payContext.put("retMsg", "total_amount is empty");
            return false;
        }
        String errorMessage;
        // 查询payOrder记录
        String payOrderId = out_trade_no;
        TPayOrder payOrder = super.baseSelectPayOrder(payOrderId);
        if (payOrder == null) {
            log.error("Can't found payOrder form db. payOrderId={}, ", payOrderId);
            payContext.put("retMsg", "Can't found payOrder");
            return false;
        }
        // 查询payChannel记录
        String mchId = payOrder.getMchid();
        String channelId = payOrder.getChannelid();
        TPayChannel payChannel = super.baseSelectPayChannel(mchId, channelId);
        if(payChannel == null) {
            log.error("Can't found payChannel form db. mchId={} channelId={}, ", payOrderId, mchId, channelId);
            payContext.put("retMsg", "Can't found payChannel");
            return false;
        }
        boolean verify_result = false;
        try {
            verify_result = AlipaySignature.rsaCheckV1(params, alipayConfig.init(payChannel.getParam()).getAlipay_public_key(), AlipayConfig.CHARSET, "RSA2");
        } catch (AlipayApiException e) {
            log.error(e.getMessage(), "AlipaySignature.rsaCheckV1 error");
        }

        // 验证签名
        if (!verify_result) {
            errorMessage = "rsaCheckV1 failed.";
            log.error("AliPay Notify parameter {}", errorMessage);
            payContext.put("retMsg", errorMessage);
            return false;
        }

        // 核对金额
        long aliPayAmt = new BigDecimal(total_amount).movePointRight(2).longValue();
        long dbPayAmt = payOrder.getAmount().longValue();
        if (dbPayAmt != aliPayAmt) {
            log.error("db payOrder record payPrice not equals total_amount. total_amount={},payOrderId={}", total_amount, payOrderId);
            payContext.put("retMsg", "");
            return false;
        }
        payContext.put("payOrder", payOrder);
        return true;
    }

    /**
     * 验证微信支付通知参数
     * @return
     */
    public boolean verifyWxPayParams(Map<String, Object> payContext) {
        WxPayOrderNotifyResult params = (WxPayOrderNotifyResult)payContext.get("parameters");

        //校验结果是否成功
        if (!PayConstant.RETURN_VALUE_SUCCESS.equalsIgnoreCase(params.getResultCode())
                && !PayConstant.RETURN_VALUE_SUCCESS.equalsIgnoreCase(params.getReturnCode())) {
            log.error("returnCode={},resultCode={},errCode={},errCodeDes={}", params.getReturnCode(), params.getResultCode(), params.getErrCode(), params.getErrCodeDes());
            payContext.put("retMsg", "notify data failed");
            return false;
        }

        Integer total_fee = params.getTotalFee();   			// 总金额
        String out_trade_no = params.getOutTradeNo();			// 商户系统订单号

        // 查询payOrder记录
        String payOrderId = out_trade_no;
        TPayOrder payOrder = super.baseSelectPayOrder(payOrderId);
        if (payOrder==null) {
            log.error("Can't found payOrder form db. payOrderId={}, ", payOrderId);
            payContext.put("retMsg", "Can't found payOrder");
            return false;
        }

        // 查询payChannel记录
        String mchId = payOrder.getMchid();
        String channelId = payOrder.getChannelid();
        TPayChannel payChannel = super.baseSelectPayChannel(mchId, channelId);
        if(payChannel == null) {
            log.error("Can't found payChannel form db. mchId={} channelId={}, ", payOrderId, mchId, channelId);
            payContext.put("retMsg", "Can't found payChannel");
            return false;
        }
        payContext.put("wxPayConfig", WxPayUtil.getWxPayConfig(payChannel.getParam()));

        // 核对金额
        long wxPayAmt = new BigDecimal(total_fee).longValue();
        long dbPayAmt = payOrder.getAmount().longValue();
        if (dbPayAmt != wxPayAmt) {
            log.error("db payOrder record payPrice not equals total_fee. total_fee={},payOrderId={}", total_fee, payOrderId);
            payContext.put("retMsg", "total_fee is not the same");
            return false;
        }

        payContext.put("payOrder", payOrder);
        return true;
    }

    public String handleAliPayNotify(Map params) {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("params", params);
        String jsonParam = RpcUtil.createBaseParam(paramMap);
        Map<String, Object> result = doAliPayNotify(jsonParam);
        String s = RpcUtil.mkRet(result);
        if(s == null) {
            return PayConstant.RETURN_ALIPAY_VALUE_FAIL;
        }
        return s;
    }

    public String handleWxPayNotify(String xmlResult) {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("xmlResult", xmlResult);
        String jsonParam = RpcUtil.createBaseParam(paramMap);
        // 返回给微信的数据格式已经有service处理(包括正确与错误),肯定会返回result
        Map<String, Object> result = doWxPayNotify(jsonParam);
        return RpcUtil.mkRet(result);
    }


}
