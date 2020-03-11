package com.easypay.cornucopiatrans.services.impl;

import com.easypay.cornucopiacommon.enums.RespCode;
import com.easypay.cornucopiacommon.enums.UserLevel;
import com.easypay.cornucopiatrans.biz.BindSettleCardBiz;
import com.easypay.cornucopiatrans.dal.pojo.UserCardSettle;
import com.easypay.cornucopiatrans.dal.pojo.UserInfo;
import com.easypay.cornucopiatrans.services.BindSettleCardService;
import com.easypay.cornucopiatrans.vo.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BindSettleCardServiceImpl implements BindSettleCardService {

    @Autowired
    private BindSettleCardBiz bindSettleCardBiz;

    @Override
    public BaseResponse bindSettleCard(String userId, String cardNo, String bankId) {
        BaseResponse baseResponse = new BaseResponse();

        bindSettleCardBiz.bindCard();

        UserCardSettle record = new UserCardSettle();
        record.setUserId(userId);
        record.setBankId(bankId);
        record.setBankName(bankId);
        record.setCardNo(cardNo);

        baseResponse.setRespCode(RespCode.CODE_000.getRespCode());
        baseResponse.setRespDesc(RespCode.CODE_000.getRespCode());
        return baseResponse;
    }
}
