package com.easypay.cornucopiatrans.services.impl;

import com.easypay.cornucopiacommon.enums.RespCode;
import com.easypay.cornucopiatrans.dal.dao.impl.TransOrdInfoMapperImpl;
import com.easypay.cornucopiatrans.dal.pojo.TransOrdInfo;
import com.easypay.cornucopiatrans.services.QueryOrderInfoServuce;
import com.easypay.cornucopiatrans.vo.response.ResultQueryOrderLit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
public class QueryOrderInfoServuceImpl implements QueryOrderInfoServuce {

    @Autowired
    private TransOrdInfoMapperImpl transOrdInfoMapper;

    @Override
    public ResultQueryOrderLit queryOrderLit(String userId) {
        ResultQueryOrderLit resultQueryOrderLit = new ResultQueryOrderLit();

        ArrayList<TransOrdInfo>  arrayList = transOrdInfoMapper.selectOrderListByUserId(userId);

        resultQueryOrderLit.setOrdInfoList(arrayList);

        resultQueryOrderLit.setRespCode(RespCode.CODE_000.getRespCode());
        resultQueryOrderLit.setRespDesc(RespCode.CODE_000.getRespDesc());
        return resultQueryOrderLit;
    }
}
