package com.easypay.cornucopiatrans.services.impl;

import com.easypay.cornucopiacommon.bean.EncryptResult;
import com.easypay.cornucopiacommon.bean.HuiFuResp;
import com.easypay.cornucopiacommon.enums.RespCode;
import com.easypay.cornucopiacommon.enums.UserLevel;
import com.easypay.cornucopiacommon.utils.EncryptUtil;
import com.easypay.cornucopiatrans.biz.BindSettleCardBiz;
import com.easypay.cornucopiatrans.biz.TransactionalBiz;
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

    @Autowired
    private EncryptUtil encryptUtil;

    @Autowired
    private TransactionalBiz transactionalBiz;

    @Override
    public BaseResponse bindSettleCard(String userId, String cardNo, String cardDistrict) {
        BaseResponse baseResponse = new BaseResponse();

        HuiFuResp huiFuResp = bindSettleCardBiz.bindCard(userId,cardNo,cardDistrict);
        if(!RespCode.CODE_000.getRespCode().equals(huiFuResp.getRespCode())){
            log.info("调用通道变更结算卡失败");
            baseResponse.setRespCode(RespCode.CODE_005.getRespCode());
            baseResponse.setRespDesc(RespCode.CODE_005.getRespDesc());
            return baseResponse;
        }

        EncryptResult encryptResult = encryptUtil.encrypt(cardNo);
        UserCardSettle record = new UserCardSettle();
        record.setUserId(userId);
        record.setCardState("1");
        record.setCardNo(encryptResult.getPlainText());
        record.setCardNoMask(encryptResult.getMaskText());
        transactionalBiz.bindCard(record);

        baseResponse.setRespCode(RespCode.CODE_000.getRespCode());
        baseResponse.setRespDesc(RespCode.CODE_000.getRespDesc());
        return baseResponse;
    }
}
