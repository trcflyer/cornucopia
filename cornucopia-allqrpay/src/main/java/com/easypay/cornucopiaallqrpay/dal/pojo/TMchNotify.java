package com.easypay.cornucopiaallqrpay.dal.pojo;

import java.util.Date;

public class TMchNotify {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_mch_notify.OrderId
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    private String orderid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_mch_notify.MchId
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    private String mchid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_mch_notify.MchOrderNo
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    private String mchorderno;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_mch_notify.OrderType
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    private String ordertype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_mch_notify.NotifyUrl
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    private String notifyurl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_mch_notify.NotifyCount
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    private Byte notifycount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_mch_notify.Result
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    private String result;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_mch_notify.Status
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    private Byte status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_mch_notify.LastNotifyTime
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    private Date lastnotifytime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_mch_notify.CreateTime
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_mch_notify.UpdateTime
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    private Date updatetime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_mch_notify.OrderId
     *
     * @return the value of t_mch_notify.OrderId
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    public String getOrderid() {
        return orderid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_mch_notify.OrderId
     *
     * @param orderid the value for t_mch_notify.OrderId
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_mch_notify.MchId
     *
     * @return the value of t_mch_notify.MchId
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    public String getMchid() {
        return mchid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_mch_notify.MchId
     *
     * @param mchid the value for t_mch_notify.MchId
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    public void setMchid(String mchid) {
        this.mchid = mchid == null ? null : mchid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_mch_notify.MchOrderNo
     *
     * @return the value of t_mch_notify.MchOrderNo
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    public String getMchorderno() {
        return mchorderno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_mch_notify.MchOrderNo
     *
     * @param mchorderno the value for t_mch_notify.MchOrderNo
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    public void setMchorderno(String mchorderno) {
        this.mchorderno = mchorderno == null ? null : mchorderno.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_mch_notify.OrderType
     *
     * @return the value of t_mch_notify.OrderType
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    public String getOrdertype() {
        return ordertype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_mch_notify.OrderType
     *
     * @param ordertype the value for t_mch_notify.OrderType
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype == null ? null : ordertype.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_mch_notify.NotifyUrl
     *
     * @return the value of t_mch_notify.NotifyUrl
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    public String getNotifyurl() {
        return notifyurl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_mch_notify.NotifyUrl
     *
     * @param notifyurl the value for t_mch_notify.NotifyUrl
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    public void setNotifyurl(String notifyurl) {
        this.notifyurl = notifyurl == null ? null : notifyurl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_mch_notify.NotifyCount
     *
     * @return the value of t_mch_notify.NotifyCount
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    public Byte getNotifycount() {
        return notifycount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_mch_notify.NotifyCount
     *
     * @param notifycount the value for t_mch_notify.NotifyCount
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    public void setNotifycount(Byte notifycount) {
        this.notifycount = notifycount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_mch_notify.Result
     *
     * @return the value of t_mch_notify.Result
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    public String getResult() {
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_mch_notify.Result
     *
     * @param result the value for t_mch_notify.Result
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_mch_notify.Status
     *
     * @return the value of t_mch_notify.Status
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_mch_notify.Status
     *
     * @param status the value for t_mch_notify.Status
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_mch_notify.LastNotifyTime
     *
     * @return the value of t_mch_notify.LastNotifyTime
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    public Date getLastnotifytime() {
        return lastnotifytime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_mch_notify.LastNotifyTime
     *
     * @param lastnotifytime the value for t_mch_notify.LastNotifyTime
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    public void setLastnotifytime(Date lastnotifytime) {
        this.lastnotifytime = lastnotifytime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_mch_notify.CreateTime
     *
     * @return the value of t_mch_notify.CreateTime
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_mch_notify.CreateTime
     *
     * @param createtime the value for t_mch_notify.CreateTime
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_mch_notify.UpdateTime
     *
     * @return the value of t_mch_notify.UpdateTime
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_mch_notify.UpdateTime
     *
     * @param updatetime the value for t_mch_notify.UpdateTime
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}