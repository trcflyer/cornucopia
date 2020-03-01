package com.easypay.cornucopiaquery.dal.pojo;

import java.util.Date;

public class UserInfo {
    private Long id;

    private String userId;

    private String userState;

    private String userName;

    private String legelName;

    private String idNo;

    private String idPhotoF;

    private String idPhotoB;

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

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState == null ? null : userState.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getLegelName() {
        return legelName;
    }

    public void setLegelName(String legelName) {
        this.legelName = legelName == null ? null : legelName.trim();
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo == null ? null : idNo.trim();
    }

    public String getIdPhotoF() {
        return idPhotoF;
    }

    public void setIdPhotoF(String idPhotoF) {
        this.idPhotoF = idPhotoF == null ? null : idPhotoF.trim();
    }

    public String getIdPhotoB() {
        return idPhotoB;
    }

    public void setIdPhotoB(String idPhotoB) {
        this.idPhotoB = idPhotoB == null ? null : idPhotoB.trim();
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