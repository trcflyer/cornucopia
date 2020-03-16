package com.easypay.cornucopiatrans.vo.response;

import com.easypay.cornucopiatrans.vo.BaseResponse;


public class ResultQueryAcctInfo extends BaseResponse {

    private String acctAvlBal;
    private String  acctBal;

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
