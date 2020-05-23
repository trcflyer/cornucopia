package com.easypay.cornucopiaallqrpay.vo.request;

import com.easypay.cornucopiaallqrpay.vo.BaseRequest;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class QueryOrderStatisVo extends BaseRequest {

    @NotBlank(message = "商户号不可为空")
    @Length(max = 16,min = 16,message = "商户号长度不合法")
    public String mchId;

    @Length(max = 64,message = "终端号长度不合法")
    public String deviceSn;

    @NotBlank(message = "起始不可为空")
    @Length(max = 8,min = 8,message = "起始长度不合法")
    public String startDate;

    @NotBlank(message = "结束不可为空")
    @Length(max = 8,min = 8,message = "结束长度不合法")
    public String endDate;

}
