package com.easypay.cornucopiatrans.controller.async;

import com.alibaba.fastjson.JSON;
import com.easypay.cornucopiatrans.dal.dao.impl.TransOrdInfoMapperImpl;
import com.easypay.cornucopiatrans.dal.pojo.TransOrdInfo;
import com.easypay.cornucopiatrans.vo.request.QuickPayDto;
import com.easypay.cornucopiatrans.vo.request.VoQuickPay;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("/trans")
public class QuickPayCallBackController {


    @Autowired
    private TransOrdInfoMapperImpl transOrdInfoMapper;

    @RequestMapping("/quickpaycallback")
    public void bindSettleCard(QuickPayDto quickPayDto){
        log.info("收到快捷支付异步回调:{}",JSON.toJSONString(quickPayDto));
        TransOrdInfo transOrdInfo = transOrdInfoMapper.selectByOrderId(quickPayDto.getOrderId());
        if (transOrdInfo == null){
            log.info("订单不存在");
        }
        if("P".equals(transOrdInfo.getOrderState())){
            transOrdInfo.setAsyncRespCode(quickPayDto.getRespCode());
            transOrdInfo.setAsyncRespDesc(quickPayDto.getRespDesc());
            transOrdInfo.setOrderState(quickPayDto.getTransStat());
            transOrdInfoMapper.updateByPrimaryKeySelective(transOrdInfo);
        }else {
            log.info("订单非中间状态,不处理");
        }
    }
}
