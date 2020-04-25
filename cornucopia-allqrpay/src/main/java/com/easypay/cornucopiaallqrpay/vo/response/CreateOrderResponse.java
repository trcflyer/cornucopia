package com.easypay.cornucopiaallqrpay.vo.response;

import com.easypay.cornucopiaallqrpay.vo.BaseResponse;

public class CreateOrderResponse extends BaseResponse {
    private String orderInfo;
    private String payOrderId;

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public String getPayOrderId() {
        return payOrderId;
    }

    public void setPayOrderId(String payOrderId) {
        this.payOrderId = payOrderId;
    }
}
