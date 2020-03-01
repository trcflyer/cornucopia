package com.easypay.cornucopiaquery.dal.pojo;

import java.util.Date;

public class UserLogin {
    private Long id;

    private String userId;

    private String loginId;

    private String loginPswd;

    private Byte pswdFailCount;

    private String userFrom;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId == null ? null : loginId.trim();
    }

    public String getLoginPswd() {
        return loginPswd;
    }

    public void setLoginPswd(String loginPswd) {
        this.loginPswd = loginPswd == null ? null : loginPswd.trim();
    }

    public Byte getPswdFailCount() {
        return pswdFailCount;
    }

    public void setPswdFailCount(Byte pswdFailCount) {
        this.pswdFailCount = pswdFailCount;
    }

    public String getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(String userFrom) {
        this.userFrom = userFrom == null ? null : userFrom.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}