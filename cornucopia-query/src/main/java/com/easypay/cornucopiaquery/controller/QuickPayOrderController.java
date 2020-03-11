package com.easypay.cornucopiaquery.controller;

import com.easypay.cornucopiacommon.result.CommonResult;
import com.easypay.cornucopiaquery.dal.dao.impl.TransOrdInfoMapperImpl;
import com.easypay.cornucopiaquery.dal.pojo.TransOrdInfo;
import com.easypay.cornucopiaquery.vo.BaseResponse;
import com.easypay.cornucopiaquery.vo.request.VoQuickPayOrderLit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 查询用户交易订单
 */
@Slf4j
@RestController("/query")
public class QuickPayOrderController {

    @Autowired
    private TransOrdInfoMapperImpl transOrdInfoMapper;

    @RequestMapping("/quickPayOrderLit")
    public CommonResult bindQuickCard(VoQuickPayOrderLit voQuickPayOrderLit) {

        List<TransOrdInfo> transOrdInfoList = transOrdInfoMapper.selectByUserId(voQuickPayOrderLit.getUserId());

        return CommonResult.success(transOrdInfoList);
    }
}
