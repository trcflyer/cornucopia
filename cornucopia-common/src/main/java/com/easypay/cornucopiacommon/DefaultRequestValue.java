package com.easypay.cornucopiacommon;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 请求汇付服务默认代理
 */

@Slf4j
@Service
public class DefaultRequestValue {

    @Value("${huifu.agentId}")
    String agentId;
    @Value("${huifu.bagentId}")
    String bagentId ;
    @Value("${huifu.oemId}")
    String oemId ;

    @Value("${quickpay.callback.transNotifyUrl}")
    String transNotifyUrl ;

    @Value("${quickpay.callback.cashNotifyUrl}")
    String cashNotifyUrl ;

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getBagentId() {
        return bagentId;
    }

    public void setBagentId(String bagentId) {
        this.bagentId = bagentId;
    }

    public String getOemId() {
        return oemId;
    }

    public void setOemId(String oemId) {
        this.oemId = oemId;
    }

    public String getTransNotifyUrl() {
        return transNotifyUrl;
    }

    public void setTransNotifyUrl(String transNotifyUrl) {
        this.transNotifyUrl = transNotifyUrl;
    }

    public String getCashNotifyUrl() {
        return cashNotifyUrl;
    }

    public void setCashNotifyUrl(String cashNotifyUrl) {
        this.cashNotifyUrl = cashNotifyUrl;
    }
}
