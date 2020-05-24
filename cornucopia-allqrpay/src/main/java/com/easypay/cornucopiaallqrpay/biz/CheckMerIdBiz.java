package com.easypay.cornucopiaallqrpay.biz;

import com.easypay.cornucopiaallqrpay.bean.MchInfoBean;
import com.easypay.cornucopiaallqrpay.dal.dao.impl.TMchInfoMapperImpl;
import com.easypay.cornucopiaallqrpay.dal.pojo.TMchInfo;
import com.easypay.cornucopiacommon.constant.Constant;
import com.easypay.cornucopiacommon.result.IErrorCode;
import com.easypay.cornucopiacommon.result.ResultCode;
import com.easypay.cornucopiacommon.utils.GuavaCacheUtil;
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

    public ResultCode checkMer(String mchId,String subMchId,String deviceSn){
        MchInfoBean mchInfoBean = (MchInfoBean) GuavaCacheUtil.get("INFO_"+mchId+subMchId);
        if(mchInfoBean == null){
            TMchInfo tMchInfo = mchInfoMapper.selectByMchId(mchId);
            if (null == tMchInfo){
                return ResultCode.CODE_100;
            }
            if (!Constant.STATE_1.equals(tMchInfo.getState())){
                return ResultCode.CODE_101;
            }
            mchInfoBean = new MchInfoBean();
            mchInfoBean.setMchId(tMchInfo.getMchId());
            mchInfoBean.setName(tMchInfo.getName());
            mchInfoBean.setState(tMchInfo.getState());
            GuavaCacheUtil.put("INFO_"+deviceSn,mchInfoBean);
        }else {
            if (!Constant.STATE_1.equals(mchInfoBean.getState())){
                return ResultCode.CODE_101;
            }
        }
        return ResultCode.SUCCESS;
    }
}
