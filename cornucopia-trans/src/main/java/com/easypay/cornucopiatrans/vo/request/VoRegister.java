package com.easypay.cornucopiatrans.vo.request;

public class VoRegister {
    private String loginId;
    private String loginPswd;
    private String userFrom;


    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(String userFrom) {
        this.userFrom = userFrom;
    }

    public String getLoginPswd() {
        return loginPswd;
    }

    public void setLoginPswd(String loginPswd) {
        this.loginPswd = loginPswd;
    }

}
