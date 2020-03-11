package com.easypay.cornucopiatrans.biz;

import com.easypay.cornucopiacommon.bean.HuiFuResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class QuickPayBiz {

    public HuiFuResp trans(){
        HuiFuResp huiFuResp = new HuiFuResp("000","成功");

        return huiFuResp;
    }

}
