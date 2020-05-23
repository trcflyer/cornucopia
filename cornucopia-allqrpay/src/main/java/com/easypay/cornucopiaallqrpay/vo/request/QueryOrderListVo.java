package com.easypay.cornucopiaallqrpay.vo.request;

import com.easypay.cornucopiaallqrpay.vo.BaseRequest;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class QueryOrderListVo extends BaseRequest {

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

    @NotBlank(message = "页码不可为空")
    @Length(max = 10,min = 1,message = "页码长度不合法")
    public String pageNum;

    @NotBlank(message = "每页显示数量不可为空")
    @Length(max = 2,min = 1,message = "每页显示数量长度不合法")
    public String pageSize;

    @Length(max = 16,message = "支付方式长度不合法")
    public String scene;

}
