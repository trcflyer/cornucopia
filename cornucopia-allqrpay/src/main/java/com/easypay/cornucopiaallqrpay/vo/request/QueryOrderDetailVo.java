package com.easypay.cornucopiaallqrpay.vo.request;

import com.easypay.cornucopiaallqrpay.vo.BaseRequest;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class QueryOrderDetailVo extends BaseRequest {

    @NotBlank(message = "商户号不可为空")
    @Length(max = 16,min = 16,message = "商户号长度不合法")
    public String mchId;

    @NotBlank(message = "终端号不可为空")
    @Length(max = 64,message = "终端号长度不合法")
    public String deviceSn;

    @NotBlank(message = "商户订单号不可为空")
    @Length(max = 32,message = "商户订单号长度不合法")
    public String mchOrderId;

    @NotBlank(message = "支付订单号不可为空")
    @Length(max = 32,message = "支付订单号长度不合法")
    public String payOrderId;

    public String executeNotify;

}
