package com.easypay.cornucopiaallqrpay.vo.response;

import com.easypay.cornucopiaallqrpay.vo.BaseResponse;

public class CreateOrderResponse extends BaseResponse {
    private String orderInfo;
    private String mchOrderId;
    private String mchId;

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getMchOrderId() {
        return mchOrderId;
    }

    public void setMchOrderId(String mchOrderId) {
        this.mchOrderId = mchOrderId;
    }

}
