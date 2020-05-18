package com.easypay.cornucopiaallqrpay.dal.pojo;

import java.util.Date;

public class TRefundOrder {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_refund_order.RefundOrderId
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    private String refundorderid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_refund_order.PayOrderId
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    private String payorderid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_refund_order.ChannelPayOrderNo
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    private String channelpayorderno;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_refund_order.MchId
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    private String mchid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_refund_order.MchRefundNo
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    private String mchrefundno;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_refund_order.ChannelId
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    private String channelid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_refund_order.PayAmount
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    private Long payamount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_refund_order.RefundAmount
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    private Long refundamount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_refund_order.Currency
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    private String currency;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_refund_order.Status
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    private Byte status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_refund_order.Result
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    private Byte result;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_refund_order.ClientIp
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    private String clientip;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_refund_order.Device
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    private String device;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_refund_order.RemarkInfo
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    private String remarkinfo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_refund_order.ChannelUser
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    private String channeluser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_refund_order.UserName
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_refund_order.ChannelMchId
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    private String channelmchid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_refund_order.ChannelOrderNo
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    private String channelorderno;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_refund_order.ChannelErrCode
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    private String channelerrcode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_refund_order.ChannelErrMsg
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    private String channelerrmsg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_refund_order.Extra
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    private String extra;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_refund_order.NotifyUrl
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    private String notifyurl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_refund_order.Param1
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    private String param1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_refund_order.Param2
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    private String param2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_refund_order.ExpireTime
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    private Date expiretime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_refund_order.RefundSuccTime
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    private Date refundsucctime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_refund_order.CreateTime
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_refund_order.UpdateTime
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    private Date updatetime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_refund_order.RefundOrderId
     *
     * @return the value of t_refund_order.RefundOrderId
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public String getRefundorderid() {
        return refundorderid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_refund_order.RefundOrderId
     *
     * @param refundorderid the value for t_refund_order.RefundOrderId
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public void setRefundorderid(String refundorderid) {
        this.refundorderid = refundorderid == null ? null : refundorderid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_refund_order.PayOrderId
     *
     * @return the value of t_refund_order.PayOrderId
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public String getPayorderid() {
        return payorderid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_refund_order.PayOrderId
     *
     * @param payorderid the value for t_refund_order.PayOrderId
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public void setPayorderid(String payorderid) {
        this.payorderid = payorderid == null ? null : payorderid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_refund_order.ChannelPayOrderNo
     *
     * @return the value of t_refund_order.ChannelPayOrderNo
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public String getChannelpayorderno() {
        return channelpayorderno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_refund_order.ChannelPayOrderNo
     *
     * @param channelpayorderno the value for t_refund_order.ChannelPayOrderNo
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public void setChannelpayorderno(String channelpayorderno) {
        this.channelpayorderno = channelpayorderno == null ? null : channelpayorderno.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_refund_order.MchId
     *
     * @return the value of t_refund_order.MchId
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public String getMchid() {
        return mchid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_refund_order.MchId
     *
     * @param mchid the value for t_refund_order.MchId
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public void setMchid(String mchid) {
        this.mchid = mchid == null ? null : mchid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_refund_order.MchRefundNo
     *
     * @return the value of t_refund_order.MchRefundNo
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public String getMchrefundno() {
        return mchrefundno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_refund_order.MchRefundNo
     *
     * @param mchrefundno the value for t_refund_order.MchRefundNo
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public void setMchrefundno(String mchrefundno) {
        this.mchrefundno = mchrefundno == null ? null : mchrefundno.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_refund_order.ChannelId
     *
     * @return the value of t_refund_order.ChannelId
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public String getChannelid() {
        return channelid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_refund_order.ChannelId
     *
     * @param channelid the value for t_refund_order.ChannelId
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public void setChannelid(String channelid) {
        this.channelid = channelid == null ? null : channelid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_refund_order.PayAmount
     *
     * @return the value of t_refund_order.PayAmount
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public Long getPayamount() {
        return payamount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_refund_order.PayAmount
     *
     * @param payamount the value for t_refund_order.PayAmount
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public void setPayamount(Long payamount) {
        this.payamount = payamount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_refund_order.RefundAmount
     *
     * @return the value of t_refund_order.RefundAmount
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public Long getRefundamount() {
        return refundamount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_refund_order.RefundAmount
     *
     * @param refundamount the value for t_refund_order.RefundAmount
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public void setRefundamount(Long refundamount) {
        this.refundamount = refundamount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_refund_order.Currency
     *
     * @return the value of t_refund_order.Currency
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_refund_order.Currency
     *
     * @param currency the value for t_refund_order.Currency
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_refund_order.Status
     *
     * @return the value of t_refund_order.Status
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_refund_order.Status
     *
     * @param status the value for t_refund_order.Status
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_refund_order.Result
     *
     * @return the value of t_refund_order.Result
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public Byte getResult() {
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_refund_order.Result
     *
     * @param result the value for t_refund_order.Result
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public void setResult(Byte result) {
        this.result = result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_refund_order.ClientIp
     *
     * @return the value of t_refund_order.ClientIp
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public String getClientip() {
        return clientip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_refund_order.ClientIp
     *
     * @param clientip the value for t_refund_order.ClientIp
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public void setClientip(String clientip) {
        this.clientip = clientip == null ? null : clientip.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_refund_order.Device
     *
     * @return the value of t_refund_order.Device
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public String getDevice() {
        return device;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_refund_order.Device
     *
     * @param device the value for t_refund_order.Device
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public void setDevice(String device) {
        this.device = device == null ? null : device.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_refund_order.RemarkInfo
     *
     * @return the value of t_refund_order.RemarkInfo
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public String getRemarkinfo() {
        return remarkinfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_refund_order.RemarkInfo
     *
     * @param remarkinfo the value for t_refund_order.RemarkInfo
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public void setRemarkinfo(String remarkinfo) {
        this.remarkinfo = remarkinfo == null ? null : remarkinfo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_refund_order.ChannelUser
     *
     * @return the value of t_refund_order.ChannelUser
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public String getChanneluser() {
        return channeluser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_refund_order.ChannelUser
     *
     * @param channeluser the value for t_refund_order.ChannelUser
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public void setChanneluser(String channeluser) {
        this.channeluser = channeluser == null ? null : channeluser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_refund_order.UserName
     *
     * @return the value of t_refund_order.UserName
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_refund_order.UserName
     *
     * @param username the value for t_refund_order.UserName
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_refund_order.ChannelMchId
     *
     * @return the value of t_refund_order.ChannelMchId
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public String getChannelmchid() {
        return channelmchid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_refund_order.ChannelMchId
     *
     * @param channelmchid the value for t_refund_order.ChannelMchId
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public void setChannelmchid(String channelmchid) {
        this.channelmchid = channelmchid == null ? null : channelmchid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_refund_order.ChannelOrderNo
     *
     * @return the value of t_refund_order.ChannelOrderNo
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public String getChannelorderno() {
        return channelorderno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_refund_order.ChannelOrderNo
     *
     * @param channelorderno the value for t_refund_order.ChannelOrderNo
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public void setChannelorderno(String channelorderno) {
        this.channelorderno = channelorderno == null ? null : channelorderno.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_refund_order.ChannelErrCode
     *
     * @return the value of t_refund_order.ChannelErrCode
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public String getChannelerrcode() {
        return channelerrcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_refund_order.ChannelErrCode
     *
     * @param channelerrcode the value for t_refund_order.ChannelErrCode
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public void setChannelerrcode(String channelerrcode) {
        this.channelerrcode = channelerrcode == null ? null : channelerrcode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_refund_order.ChannelErrMsg
     *
     * @return the value of t_refund_order.ChannelErrMsg
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public String getChannelerrmsg() {
        return channelerrmsg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_refund_order.ChannelErrMsg
     *
     * @param channelerrmsg the value for t_refund_order.ChannelErrMsg
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public void setChannelerrmsg(String channelerrmsg) {
        this.channelerrmsg = channelerrmsg == null ? null : channelerrmsg.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_refund_order.Extra
     *
     * @return the value of t_refund_order.Extra
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public String getExtra() {
        return extra;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_refund_order.Extra
     *
     * @param extra the value for t_refund_order.Extra
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public void setExtra(String extra) {
        this.extra = extra == null ? null : extra.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_refund_order.NotifyUrl
     *
     * @return the value of t_refund_order.NotifyUrl
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public String getNotifyurl() {
        return notifyurl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_refund_order.NotifyUrl
     *
     * @param notifyurl the value for t_refund_order.NotifyUrl
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public void setNotifyurl(String notifyurl) {
        this.notifyurl = notifyurl == null ? null : notifyurl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_refund_order.Param1
     *
     * @return the value of t_refund_order.Param1
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public String getParam1() {
        return param1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_refund_order.Param1
     *
     * @param param1 the value for t_refund_order.Param1
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public void setParam1(String param1) {
        this.param1 = param1 == null ? null : param1.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_refund_order.Param2
     *
     * @return the value of t_refund_order.Param2
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public String getParam2() {
        return param2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_refund_order.Param2
     *
     * @param param2 the value for t_refund_order.Param2
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public void setParam2(String param2) {
        this.param2 = param2 == null ? null : param2.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_refund_order.ExpireTime
     *
     * @return the value of t_refund_order.ExpireTime
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public Date getExpiretime() {
        return expiretime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_refund_order.ExpireTime
     *
     * @param expiretime the value for t_refund_order.ExpireTime
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public void setExpiretime(Date expiretime) {
        this.expiretime = expiretime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_refund_order.RefundSuccTime
     *
     * @return the value of t_refund_order.RefundSuccTime
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public Date getRefundsucctime() {
        return refundsucctime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_refund_order.RefundSuccTime
     *
     * @param refundsucctime the value for t_refund_order.RefundSuccTime
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public void setRefundsucctime(Date refundsucctime) {
        this.refundsucctime = refundsucctime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_refund_order.CreateTime
     *
     * @return the value of t_refund_order.CreateTime
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_refund_order.CreateTime
     *
     * @param createtime the value for t_refund_order.CreateTime
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_refund_order.UpdateTime
     *
     * @return the value of t_refund_order.UpdateTime
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_refund_order.UpdateTime
     *
     * @param updatetime the value for t_refund_order.UpdateTime
     *
     * @mbggenerated Wed May 13 21:32:40 CST 2020
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}