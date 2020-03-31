package com.easypay.cornucopiaallqrpay.service;

import com.alibaba.fastjson.JSONObject;
import com.easypay.cornucopiaallqrpay.dal.pojo.TMchInfo;
import com.easypay.cornucopiaallqrpay.dal.pojo.TPayOrder;
import com.easypay.cornucopiacommon.constant.PayConstant;
import com.easypay.cornucopiacommon.utils.PayDigestUtil;
import com.easypay.cornucopiacommon.utils.XXPayUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 支付通知处理基类
 * @author dingzhiwei jmdhappy@126.com
 * @date 2017-07-05
 * @version V1.0
 * @Copyright: www.xxpay.org
 */
@Component
@Slf4j
public class Notify4BasePay extends BaseService {


	/**
	 * 创建响应URL
	 * @param payOrder
	 * @param backType 1：前台页面；2：后台接口
	 * @return
	 */
	public String createNotifyUrl(TPayOrder payOrder, String backType) {
		String mchId = payOrder.getMchid();
		TMchInfo mchInfo = super.baseSelectMchInfo(mchId);
		String resKey = mchInfo.getReskey();
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("payOrderId", payOrder.getPayorderid() == null ? "" : payOrder.getPayorderid());           // 支付订单号
		paramMap.put("mchId", payOrder.getMchid() == null ? "" : payOrder.getMchid());                      	// 商户ID
		paramMap.put("mchOrderNo", payOrder.getMchorderno() == null ? "" : payOrder.getMchorderno());       	// 商户订单号
		paramMap.put("channelId", payOrder.getChannelid() == null ? "" : payOrder.getChannelid());              // 渠道ID
		paramMap.put("amount", payOrder.getAmount() == null ? "" : payOrder.getAmount());                      	// 支付金额
		paramMap.put("currency", payOrder.getCurrency() == null ? "" : payOrder.getCurrency());                 // 货币类型
		paramMap.put("status", payOrder.getStatus() == null ? "" : payOrder.getStatus());               		// 支付状态
		paramMap.put("clientIp", payOrder.getClientip()==null ? "" : payOrder.getClientip());   				// 客户端IP
		paramMap.put("device", payOrder.getDevice()==null ? "" : payOrder.getDevice());               			// 设备
		paramMap.put("subject", payOrder.getSubject()==null ? "" : payOrder.getSubject());     	   				// 商品标题
		paramMap.put("channelOrderNo", payOrder.getChannelorderno()==null ? "" : payOrder.getChannelorderno()); // 渠道订单号
		paramMap.put("param1", payOrder.getParam1()==null ? "" : payOrder.getParam1());               		   	// 扩展参数1
		paramMap.put("param2", payOrder.getParam2()==null ? "" : payOrder.getParam2());               		   	// 扩展参数2
		paramMap.put("paySuccTime", payOrder.getPaysucctime()==null ? "" : payOrder.getPaysucctime());			// 支付成功时间
		paramMap.put("backType", backType==null ? "" : backType);
		// 先对原文签名
		String reqSign = PayDigestUtil.getSign(paramMap, resKey);
		paramMap.put("sign", reqSign);   // 签名
		// 签名后再对有中文参数编码
		try {
			paramMap.put("device", URLEncoder.encode(payOrder.getDevice()==null ? "" : payOrder.getDevice(), PayConstant.RESP_UTF8));
			paramMap.put("subject", URLEncoder.encode(payOrder.getSubject()==null ? "" : payOrder.getSubject(), PayConstant.RESP_UTF8));
			paramMap.put("param1", URLEncoder.encode(payOrder.getParam1()==null ? "" : payOrder.getParam1(), PayConstant.RESP_UTF8));
			paramMap.put("param2", URLEncoder.encode(payOrder.getParam2()==null ? "" : payOrder.getParam2(), PayConstant.RESP_UTF8));
		}catch (UnsupportedEncodingException e) {
			log.error("URL Encode exception.", e);
			return null;
		}
		String param = XXPayUtil.genUrlParams(paramMap);
		StringBuffer sb = new StringBuffer();
		sb.append(payOrder.getNotifyurl()).append("?").append(param);
		return sb.toString();
	}

	/**
	 * 处理支付结果前台页面跳转
	 */
	public boolean doPage(TPayOrder payOrder) {
		String redirectUrl = createNotifyUrl(payOrder, "1");
		log.info("redirect to respUrl:"+redirectUrl);
		// 前台跳转业务系统
		/*try {
			response.sendRedirect(redirectUrl);
		} catch (IOException e) {
			_log.error("XxPay sendRedirect exception. respUrl="+redirectUrl, e);
			return false;
		}*/
		return true;
	}

	/**
	 * 处理支付结果后台服务器通知
	 */
	public void doNotify(TPayOrder payOrder) {
		log.info(">>>>>> PAY开始回调通知业务系统 <<<<<<");
		// 发起后台通知业务系统
		JSONObject object = createNotifyInfo(payOrder);
		try {
			log.info("汤仁才  处理异步结果通知TODO");
		} catch (Exception e) {
			log.error("payOrderId={},sendMessage error.", payOrder != null ? payOrder.getPayorderid() : "", e);
		}
		log.info(">>>>>> PAY回调通知业务系统完成 <<<<<<");
	}

	public JSONObject createNotifyInfo(TPayOrder payOrder) {
		JSONObject object = new JSONObject();
		object.put("method", "GET");
		object.put("url", createNotifyUrl(payOrder, "2"));
		object.put("orderId", payOrder.getPayorderid());
		object.put("count", payOrder.getNotifycount());
		object.put("createTime", System.currentTimeMillis());
		return object;
	}

}