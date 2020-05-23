package com.easypay.cornucopiaallqrpay.controller;

import com.alibaba.fastjson.JSONObject;
import com.easypay.cornucopiaallqrpay.biz.CheckMerIdBiz;
import com.easypay.cornucopiaallqrpay.service.IPayOrderService;
import com.easypay.cornucopiaallqrpay.util.MyChannelUtil;
import com.easypay.cornucopiaallqrpay.vo.request.QueryOrderDetailVo;
import com.easypay.cornucopiaallqrpay.vo.request.QueryOrderListVo;
import com.easypay.cornucopiaallqrpay.vo.request.QueryOrderStatisVo;
import com.easypay.cornucopiacommon.result.CommonResult;
import com.easypay.cornucopiacommon.result.ResultCode;
import com.easypay.cornucopiacommon.utils.AmountUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 支付订单查询
 * @author dingzhiwei jmdhappy@126.com
 * @date 2017-08-31
 * @version V1.0
 * @Copyright: www.xxpay.org
 */
@RestController
@Slf4j
@RequestMapping("/query")
public class QueryPayOrderController {

    @Autowired
    private CheckMerIdBiz checkMerIdBiz;

    @Autowired
    private IPayOrderService payOrderService;


    /**
     * 查询支付订单接口:
     * 1)先验证接口参数以及签名信息
     * 2)根据参数查询订单
     * 3)返回订单数据
     * @param queryOrderDetailVo
     * @return
     */

    @RequestMapping(value = "/pay/orderdetail")
    public CommonResult queryPayOrder(QueryOrderDetailVo queryOrderDetailVo) {
        log.info("###### 开始接收商户查询支付订单请求 ######");
        String logPrefix = "【商户支付订单查询】";
        try {
            if (StringUtils.isBlank(queryOrderDetailVo.getMchOrderId()) && StringUtils.isBlank(queryOrderDetailVo.getPayOrderId())) {
                log.warn("mchOrderId 和 payOrder 不可同时为空");
                return CommonResult.failed(ResultCode.CODE_999,"商户订单号和支付订单号不可同时为空");
            }

            JSONObject payOrder = payOrderService.queryPayOrder(
                    queryOrderDetailVo.getMchId(), queryOrderDetailVo.getDeviceSn(),queryOrderDetailVo.getPayOrderId(),
                    queryOrderDetailVo.getMchOrderId(), queryOrderDetailVo.getExecuteNotify());
            log.info("{}查询支付订单,结果:{}", logPrefix, payOrder);
            if (payOrder == null) {
                return CommonResult.failed(ResultCode.CODE_999,"支付订单不存在");
            }
            log.info("###### 商户查询订单处理完成 ######");
            Map<String,String> mapResult = new HashMap<>();
            mapResult.put("mchId",queryOrderDetailVo.getMchId());
            mapResult.put("mchOrderId",payOrder.getString("mchOrderId"));
            mapResult.put("payOrderId",payOrder.getString("payOrderId"));
            mapResult.put("ordAmt",AmountUtil.convertCent2Dollar(String.valueOf(payOrder.get("amount"))));
            mapResult.put("ordStatus",payOrder.getString("status"));
            mapResult.put("scene", MyChannelUtil.getSceneName(payOrder.getString("param1")));
            return CommonResult.success(JSONObject.toJSONString(mapResult));
        }catch (Exception e) {
            log.error(e.getMessage(), "");
            return CommonResult.failed(ResultCode.CODE_998,"支付系统异常");
        }
    }

    /**
     * 查询支付订单接口:
     * 1)先验证接口参数以及签名信息
     * 2)根据参数查询订单
     * 3)返回订单数据
     * @param queryOrderListVo
     * @return
     */

    @RequestMapping(value = "/pay/orderlist")
    public CommonResult queryPayOrderList(QueryOrderListVo queryOrderListVo) {
        log.info("###### 开始接收商户查询支付订单列表请求 ######");
        String logPrefix = "【商户支付订单列表查询】";
        try {
            JSONObject orderInfo = payOrderService.queryPayOrderList(
                    queryOrderListVo.getMchId(), queryOrderListVo.getDeviceSn(),queryOrderListVo.getPageNum(),queryOrderListVo.getPageSize()
                    ,queryOrderListVo.getStartDate(),queryOrderListVo.getEndDate(),queryOrderListVo.getScene());
            log.info("{}查询支付订单,结果:{}", logPrefix, orderInfo);
            if (orderInfo == null) {
                return CommonResult.failed(ResultCode.CODE_999,"支付订单列表为空");
            }
            Map<String,String> mapResult = new HashMap<>();
            mapResult.put("mchId",queryOrderListVo.getMchId());
            mapResult.putAll(JSONObject.parseObject(orderInfo.toJSONString(),HashMap.class));
            return CommonResult.success(JSONObject.toJSONString(mapResult));
        }catch (Exception e) {
            log.error(e.getMessage(), "");
            return CommonResult.failed(ResultCode.CODE_998,"支付系统异常");
        }
    }

    /**
     * 商户交易统计查询
     * @param queryOrderStatisVo
     * @return
     */
    @RequestMapping(value = "/pay/orderstatis")
    public CommonResult queryPayOrderStatis(QueryOrderStatisVo queryOrderStatisVo) {
        log.info("###### 开始接收商户交易统计查询请求 ######");
        String logPrefix = "【商户交易统计查询】";
        try {
            JSONObject orderInfo = payOrderService.queryPayOrderStatistics(queryOrderStatisVo.getMchId()
                    , queryOrderStatisVo.getDeviceSn(),queryOrderStatisVo.getStartDate(),queryOrderStatisVo.getEndDate());
            log.info("{}查询支付订单,结果:{}", logPrefix, orderInfo);
            if (orderInfo == null) {
                return CommonResult.failed(ResultCode.CODE_999,"商户交易统计查询结果为空");
            }
            Map<String,String> mapResult = new HashMap<>();
            mapResult.put("mchId",queryOrderStatisVo.getMchId());
            mapResult.putAll(JSONObject.parseObject(orderInfo.toJSONString(),HashMap.class));
            return CommonResult.success(JSONObject.toJSONString(mapResult));
        }catch (Exception e) {
            log.error(e.getMessage(), "");
            return CommonResult.failed(ResultCode.CODE_998,"支付系统异常");
        }
    }
}
