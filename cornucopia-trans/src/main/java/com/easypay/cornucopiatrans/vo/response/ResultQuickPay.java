package com.easypay.cornucopiatrans.vo.response;

import com.easypay.cornucopiatrans.vo.BaseResponse;


public class ResultQuickPay extends BaseResponse {

    private String quickPayUrl;

    public String getQuickPayUrl() {
        return quickPayUrl;
    }

    public void setQuickPayUrl(String quickPayUrl) {
        this.quickPayUrl = quickPayUrl;
    }
}
