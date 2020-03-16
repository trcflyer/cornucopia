package com.easypay.cornucopiatrans.vo.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class VoCheckName {

    @NotNull(message="用户id不能为空")
    @NotEmpty(message="用户id不能为空")
    @Length(min=16,max=16,message="用户id长度不合法")
    private String userId;

    @NotNull(message = "身份证号不可为空")
    @NotEmpty(message = "身份证号不可为空")
    @Pattern(regexp="^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)$",message="身份证号不合规")
    private String idNo;

    @NotNull(message = "身份证姓名不可为空")
    @NotEmpty(message = "身份证姓名必须在1-20位")
    private String legalName;

    @NotNull(message = "店铺名称不可为空")
    @Length(min=1,max=10,message="店铺名称必须在1-10位")
    private String userName;
}
