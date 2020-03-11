package com.easypay.cornucopiatrans.dal.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class TransOrdInfo {
    private Long id;

    private String transDate;

    private String sqeId;

    private String orderId;

    private BigDecimal orderAmt;

    private String orderState;

    private BigDecimal feeAmt;

    private String userId;

    private String userLat;

    private String userLng;

    private String channelNo;

    private String quickSqeId;

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

    public BigDecimal getOrderAmt() {
        return orderAmt;
    }

    public void setOrderAmt(BigDecimal orderAmt) {
        this.orderAmt = orderAmt;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState == null ? null : orderState.trim();
    }

    public BigDecimal getFeeAmt() {
        return feeAmt;
    }

    public void setFeeAmt(BigDecimal feeAmt) {
        this.feeAmt = feeAmt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserLat() {
        return userLat;
    }

    public void setUserLat(String userLat) {
        this.userLat = userLat == null ? null : userLat.trim();
    }

    public String getUserLng() {
        return userLng;
    }

    public void setUserLng(String userLng) {
        this.userLng = userLng == null ? null : userLng.trim();
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo == null ? null : channelNo.trim();
    }

    public String getQuickSqeId() {
        return quickSqeId;
    }

    public void setQuickSqeId(String quickSqeId) {
        this.quickSqeId = quickSqeId == null ? null : quickSqeId.trim();
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