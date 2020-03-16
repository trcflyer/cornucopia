package com.easypay.cornucopiatrans.services.impl;

import com.easypay.cornucopiacommon.bean.HuiFuResp;
import com.easypay.cornucopiacommon.enums.RespCode;
import com.easypay.cornucopiatrans.biz.BindQuickCardBiz;
import com.easypay.cornucopiatrans.dal.dao.UserCardQuickMapper;
import com.easypay.cornucopiatrans.dal.pojo.UserCardQuick;
import com.easypay.cornucopiatrans.services.BindQuickCardService;
import com.easypay.cornucopiatrans.vo.BaseResponse;
import com.easypay.cornucopiatrans.vo.response.ResultQuickCard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BindQuickCardServiceImpl implements BindQuickCardService {

    @Autowired
    private BindQuickCardBiz bindQuickCardBiz;

    @Override
    public ResultQuickCard bindQuickCard(String userId) {
        ResultQuickCard baseResponse = new ResultQuickCard();

        HuiFuResp huiFuResp = bindQuickCardBiz.bindCard(userId);
        if(!RespCode.CODE_000.getRespCode().equals(huiFuResp.getRespCode())){
            log.info("调用通道绑定快捷卡失败");
            baseResponse.setRespCode(RespCode.CODE_006.getRespCode());
            baseResponse.setRespDesc(RespCode.CODE_006.getRespDesc());
            return baseResponse;
        }
        baseResponse.setBindCardUrl(huiFuResp.getBindCardUrl());
        baseResponse.setRespCode(RespCode.CODE_000.getRespCode());
        baseResponse.setRespDesc(RespCode.CODE_000.getRespDesc());
        return baseResponse;
    }
}
