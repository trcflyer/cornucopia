package com.easypay.cornucopiatrans.vo.response;

import com.easypay.cornucopiatrans.vo.BaseResponse;

public class ResultRegister extends BaseResponse {
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
