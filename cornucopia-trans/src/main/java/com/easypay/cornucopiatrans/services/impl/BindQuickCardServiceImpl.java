package com.easypay.cornucopiatrans.services.impl;

import com.easypay.cornucopiacommon.enums.RespCode;
import com.easypay.cornucopiatrans.biz.BindQuickCardBiz;
import com.easypay.cornucopiatrans.dal.pojo.UserCardQuick;
import com.easypay.cornucopiatrans.services.BindQuickCardService;
import com.easypay.cornucopiatrans.vo.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BindQuickCardServiceImpl implements BindQuickCardService {

    @Autowired
    private BindQuickCardBiz bindQuickCardBiz;

    @Override
    public BaseResponse bindQuickCard(String userId, String cardNo, String bankId) {
        BaseResponse baseResponse = new BaseResponse();

        bindQuickCardBiz.bindCard();

        UserCardQuick record = new UserCardQuick();
        record.setUserId(userId);
        record.setBankId(bankId);
        record.setBankName(bankId);
        record.setCardNo(cardNo);

        baseResponse.setRespCode(RespCode.CODE_000.getRespCode());
        baseResponse.setRespDesc(RespCode.CODE_000.getRespCode());
        return baseResponse;
    }
}
