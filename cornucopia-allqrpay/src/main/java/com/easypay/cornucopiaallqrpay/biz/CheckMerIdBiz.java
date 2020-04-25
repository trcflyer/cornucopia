package com.easypay.cornucopiaallqrpay.biz;

import com.easypay.cornucopiaallqrpay.dal.dao.impl.TMchInfoMapperImpl;
import com.easypay.cornucopiaallqrpay.dal.pojo.TMchInfo;
import com.easypay.cornucopiacommon.constant.Constant;
import com.easypay.cornucopiacommon.result.IErrorCode;
import com.easypay.cornucopiacommon.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 校验用户合法性
 */
@Slf4j
@Service
public class CheckMerIdBiz {

    @Autowired
    private TMchInfoMapperImpl mchInfoMapper;

    public ResultCode checkMer(String mchId){
       TMchInfo tMchInfo = mchInfoMapper.selectByMchId(mchId);
       if (null == tMchInfo){
           return ResultCode.CODE_100;
       }
       if (!Constant.STATE_1.equals(tMchInfo.getState())){
            return ResultCode.CODE_101;
       }
    return ResultCode.SUCCESS;
    }
}
