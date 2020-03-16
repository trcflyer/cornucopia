package com.easypay.cornucopiacommon.exception;

public class BizException extends RuntimeException {
    private static final long serialVersionUID = -8716100844379461082L;
    private String code;//自定义异常码

    private String message;

    public BizException(String code,String message){
        super(message);// 父类的构造函数；调用底层的Throwable的构造函数，将参数message赋值到detailMessage (Throwable的属性)
        this.code = code;//赋值code码
        this.message = message;
    }
    public BizException(String message){
        super(message);// 父类的构造函数；调用底层的Throwable的构造函数，将参数message赋值到detailMessage (Throwable的属性)
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
