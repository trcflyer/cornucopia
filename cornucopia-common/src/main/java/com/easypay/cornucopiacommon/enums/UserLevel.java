package com.easypay.cornucopiacommon.enums;

public enum UserLevel {
    ONE(1,"铁牌"),
    TWO(2,"铜牌"),
    THREE(3,"银牌"),
    FORE(4,"金牌")
    ;
    // 成员变量
    private int userLevel;
    private String desc;

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    // 构造方法
    private UserLevel(int userLevel,String desc) {
        this.userLevel = userLevel;
        this.desc = desc;
    }
}
