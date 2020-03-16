package com.easypay.cornucopiatrans.services.impl;

import com.easypay.cornucopiacommon.bean.HuiFuResp;
import com.easypay.cornucopiacommon.enums.RespCode;
import com.easypay.cornucopiatrans.biz.QueryAccoutInfoBiz;
import com.easypay.cornucopiatrans.services.QueryAccoutInfoService;
import com.easypay.cornucopiatrans.vo.response.ResultQueryAcctInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class QueryAccoutInfoServiceImpl implements QueryAccoutInfoService {

    @Autowired
    private QueryAccoutInfoBiz queryAccoutInfoBiz;

    @Override
    public ResultQueryAcctInfo qyeryAcct(String userId) {
        ResultQueryAcctInfo resultQueryAcctInfo = new ResultQueryAcctInfo();

        HuiFuResp huiFuResp = queryAccoutInfoBiz.queryAccoutInfo(userId);
        if(!RespCode.CODE_000.getRespCode().equals(huiFuResp.getRespCode())){
            log.info("调用通道绑定快捷卡失败");
            resultQueryAcctInfo.setRespCode(RespCode.CODE_006.getRespCode());
            resultQueryAcctInfo.setRespDesc(RespCode.CODE_006.getRespDesc());
            return resultQueryAcctInfo;
        }
        resultQueryAcctInfo.setAcctAvlBal(huiFuResp.getAcctAvlBal());
        resultQueryAcctInfo.setAcctBal(huiFuResp.getAcctBal());
        resultQueryAcctInfo.setRespCode(RespCode.CODE_000.getRespCode());
        resultQueryAcctInfo.setRespDesc(RespCode.CODE_000.getRespDesc());
        return resultQueryAcctInfo;
    }
}
