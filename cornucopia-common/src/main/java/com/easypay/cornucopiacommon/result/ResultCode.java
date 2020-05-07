package com.easypay.cornucopiacommon.result;

/**
 * 枚举了一些常用API操作码
 * Created by macro on 2019/4/19.
 */
public enum ResultCode implements IErrorCode {
    SUCCESS("000", "成功"),
    FAILED("500", "失败"),
    VALIDATE_FAILED("404", "参数检验失败"),
    UNAUTHORIZED("401", "暂未登录或token已经过期"),
    FORBIDDEN("403", "没有相关权限"),

    CODE_100("100","用户不存在"),
    CODE_101("101","用户状态不合法"),
    CODE_200("200","下单失败"),
    CODE_201("201","订单已经存在"),
    CODE_202("202","支付失败"),
    CODE_203("203","支付处理中"),


    CODE_998("998","其他异常"),
    CODE_999("999","请求参数错误");
    private String code;
    private String message;

    private ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
