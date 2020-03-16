package com.easypay.cornucopiacommon.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class HuiFuResp {

    private String respCode;
    private String respDesc;
    private String memberId;
    private String bindCardUrl;
    private String acctAvlBal;
    private String acctBal;



    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespDesc() {
        return respDesc;
    }

    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getBindCardUrl() {
        return bindCardUrl;
    }

    public void setBindCardUrl(String bindCardUrl) {
        this.bindCardUrl = bindCardUrl;
    }

    public String getAcctAvlBal() {
        return acctAvlBal;
    }

    public void setAcctAvlBal(String acctAvlBal) {
        this.acctAvlBal = acctAvlBal;
    }

    public String getAcctBal() {
        return acctBal;
    }

    public void setAcctBal(String acctBal) {
        this.acctBal = acctBal;
    }
}
