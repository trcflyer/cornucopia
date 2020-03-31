package com.easypay.cornucopiaallqrpay.dal.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class TransOrdInfo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trans_ord_info.id
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trans_ord_info.trans_date
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private String transDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trans_ord_info.sqe_id
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private String sqeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trans_ord_info.order_id
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private String orderId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trans_ord_info.order_amt
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private BigDecimal orderAmt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trans_ord_info.order_state
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private String orderState;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trans_ord_info.fee_amt
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private BigDecimal feeAmt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trans_ord_info.user_id
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trans_ord_info.user_lat
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private String userLat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trans_ord_info.user_lng
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private String userLng;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trans_ord_info.channel_no
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private String channelNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trans_ord_info.quick_sqe_id
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private String quickSqeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trans_ord_info.sync_resp_code
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private String syncRespCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trans_ord_info.sync_resp_desc
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private String syncRespDesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trans_ord_info.async_resp_code
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private String asyncRespCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trans_ord_info.async_resp_desc
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private String asyncRespDesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trans_ord_info.create_time
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trans_ord_info.update_time
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trans_ord_info.id
     *
     * @return the value of trans_ord_info.id
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trans_ord_info.id
     *
     * @param id the value for trans_ord_info.id
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trans_ord_info.trans_date
     *
     * @return the value of trans_ord_info.trans_date
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public String getTransDate() {
        return transDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trans_ord_info.trans_date
     *
     * @param transDate the value for trans_ord_info.trans_date
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setTransDate(String transDate) {
        this.transDate = transDate == null ? null : transDate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trans_ord_info.sqe_id
     *
     * @return the value of trans_ord_info.sqe_id
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public String getSqeId() {
        return sqeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trans_ord_info.sqe_id
     *
     * @param sqeId the value for trans_ord_info.sqe_id
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setSqeId(String sqeId) {
        this.sqeId = sqeId == null ? null : sqeId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trans_ord_info.order_id
     *
     * @return the value of trans_ord_info.order_id
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trans_ord_info.order_id
     *
     * @param orderId the value for trans_ord_info.order_id
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trans_ord_info.order_amt
     *
     * @return the value of trans_ord_info.order_amt
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public BigDecimal getOrderAmt() {
        return orderAmt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trans_ord_info.order_amt
     *
     * @param orderAmt the value for trans_ord_info.order_amt
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setOrderAmt(BigDecimal orderAmt) {
        this.orderAmt = orderAmt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trans_ord_info.order_state
     *
     * @return the value of trans_ord_info.order_state
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public String getOrderState() {
        return orderState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trans_ord_info.order_state
     *
     * @param orderState the value for trans_ord_info.order_state
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setOrderState(String orderState) {
        this.orderState = orderState == null ? null : orderState.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trans_ord_info.fee_amt
     *
     * @return the value of trans_ord_info.fee_amt
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public BigDecimal getFeeAmt() {
        return feeAmt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trans_ord_info.fee_amt
     *
     * @param feeAmt the value for trans_ord_info.fee_amt
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setFeeAmt(BigDecimal feeAmt) {
        this.feeAmt = feeAmt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trans_ord_info.user_id
     *
     * @return the value of trans_ord_info.user_id
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trans_ord_info.user_id
     *
     * @param userId the value for trans_ord_info.user_id
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trans_ord_info.user_lat
     *
     * @return the value of trans_ord_info.user_lat
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public String getUserLat() {
        return userLat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trans_ord_info.user_lat
     *
     * @param userLat the value for trans_ord_info.user_lat
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setUserLat(String userLat) {
        this.userLat = userLat == null ? null : userLat.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trans_ord_info.user_lng
     *
     * @return the value of trans_ord_info.user_lng
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public String getUserLng() {
        return userLng;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trans_ord_info.user_lng
     *
     * @param userLng the value for trans_ord_info.user_lng
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setUserLng(String userLng) {
        this.userLng = userLng == null ? null : userLng.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trans_ord_info.channel_no
     *
     * @return the value of trans_ord_info.channel_no
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public String getChannelNo() {
        return channelNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trans_ord_info.channel_no
     *
     * @param channelNo the value for trans_ord_info.channel_no
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo == null ? null : channelNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trans_ord_info.quick_sqe_id
     *
     * @return the value of trans_ord_info.quick_sqe_id
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public String getQuickSqeId() {
        return quickSqeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trans_ord_info.quick_sqe_id
     *
     * @param quickSqeId the value for trans_ord_info.quick_sqe_id
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setQuickSqeId(String quickSqeId) {
        this.quickSqeId = quickSqeId == null ? null : quickSqeId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trans_ord_info.sync_resp_code
     *
     * @return the value of trans_ord_info.sync_resp_code
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public String getSyncRespCode() {
        return syncRespCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trans_ord_info.sync_resp_code
     *
     * @param syncRespCode the value for trans_ord_info.sync_resp_code
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setSyncRespCode(String syncRespCode) {
        this.syncRespCode = syncRespCode == null ? null : syncRespCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trans_ord_info.sync_resp_desc
     *
     * @return the value of trans_ord_info.sync_resp_desc
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public String getSyncRespDesc() {
        return syncRespDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trans_ord_info.sync_resp_desc
     *
     * @param syncRespDesc the value for trans_ord_info.sync_resp_desc
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setSyncRespDesc(String syncRespDesc) {
        this.syncRespDesc = syncRespDesc == null ? null : syncRespDesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trans_ord_info.async_resp_code
     *
     * @return the value of trans_ord_info.async_resp_code
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public String getAsyncRespCode() {
        return asyncRespCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trans_ord_info.async_resp_code
     *
     * @param asyncRespCode the value for trans_ord_info.async_resp_code
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setAsyncRespCode(String asyncRespCode) {
        this.asyncRespCode = asyncRespCode == null ? null : asyncRespCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trans_ord_info.async_resp_desc
     *
     * @return the value of trans_ord_info.async_resp_desc
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public String getAsyncRespDesc() {
        return asyncRespDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trans_ord_info.async_resp_desc
     *
     * @param asyncRespDesc the value for trans_ord_info.async_resp_desc
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setAsyncRespDesc(String asyncRespDesc) {
        this.asyncRespDesc = asyncRespDesc == null ? null : asyncRespDesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trans_ord_info.create_time
     *
     * @return the value of trans_ord_info.create_time
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trans_ord_info.create_time
     *
     * @param createTime the value for trans_ord_info.create_time
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trans_ord_info.update_time
     *
     * @return the value of trans_ord_info.update_time
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trans_ord_info.update_time
     *
     * @param updateTime the value for trans_ord_info.update_time
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}