package com.easypay.cornucopiaallqrpay.vo;

import lombok.Data;

@Data
public class BaseRequest {

    private String infVersion;//接口版本，缺省时候默认1.0.0
    private String longitude;//经度
    private String latitude;//纬度

}
