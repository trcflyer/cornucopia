package com.easypay.cornucopiacommon.enums;

public enum RespCode {
    CODE_000("000","成功"),
    CODE_001("001","用户名密码错误"),
    CODE_002("002","注册失败"),
    CODE_003("003","该手机号已经注册"),
    CODE_004("004","实名认证失败,开户失败"),
    CODE_005("005","结算卡绑定失败"),
    CODE_006("006","快捷卡绑定失败"),
    CODE_007("007","账户余额查询失败"),

    CODE_100("100","用户不存在"),
    CODE_101("101","用户状态不合法"),

    CODE_998("998","其他异常"),
    CODE_999("999","请求参数错误")
            ;

    // 成员变量
    private String RespCode;
    private String RespDesc;


    public String getRespCode() {
        return RespCode;
    }

    public void setRespCode(String respCode) {
        RespCode = respCode;
    }

    public String getRespDesc() {
        return RespDesc;
    }

    public void setRespDesc(String respDesc) {
        RespDesc = respDesc;
    }

    // 构造方法
    private RespCode(String RespCode,String respDesc) {
        this.RespCode = RespCode;
        this.RespDesc = respDesc;
    }


}
