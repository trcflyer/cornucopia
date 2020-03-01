package com.easypay.cornucopiacommon.enums;

public enum RespCode {
    CODE_000("000","成功"),
    CODE_001("001","用户名密码错误");

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
