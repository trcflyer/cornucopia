package com.easypay.cornucopiaallqrpay.biz;

import com.easypay.cornucopiaallqrpay.dal.dao.impl.TMchInfoMapperImpl;
import com.easypay.cornucopiaallqrpay.dal.dao.impl.TPayOrderMapperImpl;
import com.easypay.cornucopiaallqrpay.dal.pojo.TMchInfo;
import com.easypay.cornucopiaallqrpay.dal.pojo.TPayOrder;
import com.easypay.cornucopiacommon.constant.Constant;
import com.easypay.cornucopiacommon.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 校验用户合法性
 */
@Slf4j
@Service
public class CheckMerOrdIdBiz {

    @Autowired
    private TPayOrderMapperImpl tPayOrderMapper;

    public ResultCode checkMerOrdId(String mchId, String ordId) {
        TPayOrder tPayOrder = tPayOrderMapper.selectMchOrderId(mchId, ordId);
        if (null == tPayOrder) {
            return ResultCode.SUCCESS;
        }

        return ResultCode.CODE_201;
    }
}
