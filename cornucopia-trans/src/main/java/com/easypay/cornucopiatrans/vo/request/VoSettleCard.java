package com.easypay.cornucopiatrans.vo.request;

import com.easypay.cornucopiatrans.vo.BaseRequest;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class VoSettleCard extends BaseRequest {

    @NotNull(message="用户id不能为空")
    @NotEmpty(message="用户id不能为空")
    @Length(min=16,max=16,message="用户id长度不合法")
    private String userId;

    @NotNull(message="用户id不能为空")
    @NotEmpty(message="用户id不能为空")
    @Length(min=16,max=16,message="用户id长度不合法")
    private String cardNo;

    @NotNull(message="用户地区不能为空")
    @NotEmpty(message="用户地区不能为空")
    @Length(min=6,max=6,message="用户地区长度不合法")
    private String cardDistrict;
}
