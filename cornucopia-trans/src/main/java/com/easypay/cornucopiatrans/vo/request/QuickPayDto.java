package com.easypay.cornucopiatrans.vo.request;

import lombok.Data;

@Data
public class QuickPayDto {
    public String respCode;
    public String respDesc;
    private String orderId;
    private String merOrderId;

    private String transStat;
}
