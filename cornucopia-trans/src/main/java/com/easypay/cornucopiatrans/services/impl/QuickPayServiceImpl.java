package com.easypay.cornucopiatrans.services.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.easypay.cornucopiacommon.bean.HuiFuResp;
import com.easypay.cornucopiacommon.enums.RespCode;
import com.easypay.cornucopiatrans.biz.QuickPayBiz;
import com.easypay.cornucopiatrans.biz.SequenceBiz;
import com.easypay.cornucopiatrans.dal.dao.impl.TransOrdInfoMapperImpl;
import com.easypay.cornucopiatrans.dal.pojo.TransOrdInfo;
import com.easypay.cornucopiatrans.services.QuickPayService;
import com.easypay.cornucopiatrans.vo.request.VoQuickPay;
import com.easypay.cornucopiatrans.vo.response.ResultQuickPay;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Slf4j
@Service
public class QuickPayServiceImpl implements QuickPayService {

    @Autowired
    private QuickPayBiz quickPayBiz;

    @Autowired
    private SequenceBiz sequenceBiz;

    @Autowired
    private TransOrdInfoMapperImpl transOrdInfoMapper;

    @Override
    public ResultQuickPay doquickpay(VoQuickPay voQuickPay){
        ResultQuickPay baseResponse = new ResultQuickPay();

        TransOrdInfo transOrdInfo = new TransOrdInfo();
        initTransOrdInfo(transOrdInfo, voQuickPay);
        transOrdInfoMapper.insertSelective(transOrdInfo);

        HuiFuResp huiFuResp  = quickPayBiz.trans(voQuickPay.getUserId(),voQuickPay.getAmt(),voQuickPay.getCardSeqId()
        ,transOrdInfo.getOrderId());
        if(RespCode.CODE_000.getRespCode().equals(huiFuResp.getRespCode())){
            transOrdInfo.setOrderState("P");
        }else {
            transOrdInfo.setOrderState("F");
        }
        transOrdInfo.setSyncRespCode(huiFuResp.getRespCode());
        transOrdInfo.setSyncRespDesc(huiFuResp.getRespDesc());

        transOrdInfoMapper.updateByPrimaryKeySelective(transOrdInfo);

        baseResponse.setRespCode(RespCode.CODE_000.getRespCode());
        baseResponse.setRespDesc(RespCode.CODE_000.getRespDesc());
        return baseResponse;
    }

    /**
     * 初始化订单
     * @param transOrdInfo
     * @param voQuickPay
     */
    private void initTransOrdInfo(TransOrdInfo transOrdInfo, VoQuickPay voQuickPay){
        try {
            String transDate = DateUtil.format(new Date(), DatePattern.PURE_DATE_PATTERN);
            String orderId = sequenceBiz.getSeqId("ORD_ID_SQE");
            transOrdInfo.setTransDate(transDate);
            transOrdInfo.setSqeId(orderId);
            transOrdInfo.setOrderId(transDate+orderId);
            transOrdInfo.setOrderAmt(new BigDecimal(voQuickPay.getAmt()));
            transOrdInfo.setOrderState("I");
            transOrdInfo.setUserLat(voQuickPay.getLatitude());
            transOrdInfo.setUserLng(voQuickPay.getLongitude());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
