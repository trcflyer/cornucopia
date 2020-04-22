package com.easypay.cornucopiaallqrpay.service;

import com.alibaba.fastjson.JSONObject;
import com.easypay.cornucopiaallqrpay.dal.pojo.TMchInfo;
import com.easypay.cornucopiaallqrpay.dal.pojo.TPayOrder;
import com.easypay.cornucopiacommon.constant.PayConstant;
import com.easypay.cornucopiacommon.utils.PayDigestUtil;
import com.easypay.cornucopiacommon.utils.XXPayUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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


	@Value("${myrsa.publickey}")
	String publickey;


	/**
	 * 处理支付结果后台服务器通知
	 */
	public void doNotify(TPayOrder payOrder) {
		log.info(">>>>>> PAY开始回调通知业务系统 <<<<<<");
		// 发起后台通知业务系统
		try {
			log.info("汤仁才  处理异步结果通知TODO");
		} catch (Exception e) {
			log.error("payOrderId={},sendMessage error.", payOrder != null ? payOrder.getPayOrderId() : "", e);
		}
		log.info(">>>>>> PAY回调通知业务系统完成 <<<<<<");
	}


}
