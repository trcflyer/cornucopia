package com.easypay.cornucopiaallqrpay.vo.request;

import com.easypay.cornucopiaallqrpay.vo.BaseRequest;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class CreateOrderRequest extends BaseRequest {

    @NotBlank(message = "商户号不可为空")
    @Length(max = 20,min = 20,message = "商户号长度不合法")
    public String memberId;

    @NotBlank(message = "checkValue不可为空")
    private String checkValue;

}
