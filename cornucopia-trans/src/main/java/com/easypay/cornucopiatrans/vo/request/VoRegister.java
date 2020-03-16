package com.easypay.cornucopiatrans.vo.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class VoRegister {
    @NotNull
    @NotEmpty(message="手机号不能为空")
    private String loginId;

    @NotNull
    @NotEmpty(message="openId不能为空")
    private String openId;


}
