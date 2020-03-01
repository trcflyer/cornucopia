package com.easypay.cornucopiaquery.dal.pojo;

import java.util.Date;

public class TransOrdInfo {
    private Long id;

    private String transDate;

    private String sqeId;

    private String orderId;

    private Long orderAmt;

    private String orderState;

    private Long feeAmt;

    private String userId;

    private Long userLat;

    private Long userLng;

    private String channelNo;

    private String syncRespCode;

    private String syncRespDesc;

    private String asyncRespCode;

    private String asyncRespDesc;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate == null ? null : transDate.trim();
    }

    public String getSqeId() {
        return sqeId;
    }

    public void setSqeId(String sqeId) {
        this.sqeId = sqeId == null ? null : sqeId.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Long getOrderAmt() {
        return orderAmt;
    }

    public void setOrderAmt(Long orderAmt) {
        this.orderAmt = orderAmt;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState == null ? null : orderState.trim();
    }

    public Long getFeeAmt() {
        return feeAmt;
    }

    public void setFeeAmt(Long feeAmt) {
        this.feeAmt = feeAmt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Long getUserLat() {
        return userLat;
    }

    public void setUserLat(Long userLat) {
        this.userLat = userLat;
    }

    public Long getUserLng() {
        return userLng;
    }

    public void setUserLng(Long userLng) {
        this.userLng = userLng;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo == null ? null : channelNo.trim();
    }

    public String getSyncRespCode() {
        return syncRespCode;
    }

    public void setSyncRespCode(String syncRespCode) {
        this.syncRespCode = syncRespCode == null ? null : syncRespCode.trim();
    }

    public String getSyncRespDesc() {
        return syncRespDesc;
    }

    public void setSyncRespDesc(String syncRespDesc) {
        this.syncRespDesc = syncRespDesc == null ? null : syncRespDesc.trim();
    }

    public String getAsyncRespCode() {
        return asyncRespCode;
    }

    public void setAsyncRespCode(String asyncRespCode) {
        this.asyncRespCode = asyncRespCode == null ? null : asyncRespCode.trim();
    }

    public String getAsyncRespDesc() {
        return asyncRespDesc;
    }

    public void setAsyncRespDesc(String asyncRespDesc) {
        this.asyncRespDesc = asyncRespDesc == null ? null : asyncRespDesc.trim();
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