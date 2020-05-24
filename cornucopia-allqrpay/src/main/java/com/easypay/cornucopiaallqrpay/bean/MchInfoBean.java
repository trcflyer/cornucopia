package com.easypay.cornucopiaallqrpay.bean;

import lombok.Data;

@Data
public class MchInfoBean {
    private String mchId;
    private String subMchId;
    private String deviceSn;
    private String name;
    private Byte state;

}
