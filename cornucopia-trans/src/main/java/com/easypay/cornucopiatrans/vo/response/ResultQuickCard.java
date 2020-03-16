package com.easypay.cornucopiatrans.vo.response;

import com.easypay.cornucopiatrans.vo.BaseRequest;
import com.easypay.cornucopiatrans.vo.BaseResponse;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class ResultQuickCard extends BaseResponse {

    private String bindCardUrl;

    public String getBindCardUrl() {
        return bindCardUrl;
    }

    public void setBindCardUrl(String bindCardUrl) {
        this.bindCardUrl = bindCardUrl;
    }
}
