package com.easypay.cornucopiaallqrpay.dal.pojo;

import java.util.Date;

public class TTransOrder {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trans_order.TransOrderId
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private String transorderid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trans_order.MchId
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private String mchid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trans_order.MchTransNo
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private String mchtransno;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trans_order.ChannelId
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private String channelid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trans_order.Amount
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private Long amount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trans_order.Currency
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private String currency;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trans_order.Status
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private Byte status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trans_order.Result
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private Byte result;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trans_order.ClientIp
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private String clientip;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trans_order.Device
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private String device;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trans_order.RemarkInfo
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private String remarkinfo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trans_order.ChannelUser
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private String channeluser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trans_order.UserName
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trans_order.ChannelMchId
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private String channelmchid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trans_order.ChannelOrderNo
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private String channelorderno;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trans_order.ChannelErrCode
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private String channelerrcode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trans_order.ChannelErrMsg
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private String channelerrmsg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trans_order.Extra
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private String extra;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trans_order.NotifyUrl
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private String notifyurl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trans_order.Param1
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private String param1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trans_order.Param2
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private String param2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trans_order.ExpireTime
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private Date expiretime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trans_order.TransSuccTime
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private Date transsucctime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trans_order.CreateTime
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trans_order.UpdateTime
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private Date updatetime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trans_order.TransOrderId
     *
     * @return the value of t_trans_order.TransOrderId
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public String getTransorderid() {
        return transorderid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trans_order.TransOrderId
     *
     * @param transorderid the value for t_trans_order.TransOrderId
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setTransorderid(String transorderid) {
        this.transorderid = transorderid == null ? null : transorderid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trans_order.MchId
     *
     * @return the value of t_trans_order.MchId
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public String getMchid() {
        return mchid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trans_order.MchId
     *
     * @param mchid the value for t_trans_order.MchId
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setMchid(String mchid) {
        this.mchid = mchid == null ? null : mchid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trans_order.MchTransNo
     *
     * @return the value of t_trans_order.MchTransNo
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public String getMchtransno() {
        return mchtransno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trans_order.MchTransNo
     *
     * @param mchtransno the value for t_trans_order.MchTransNo
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setMchtransno(String mchtransno) {
        this.mchtransno = mchtransno == null ? null : mchtransno.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trans_order.ChannelId
     *
     * @return the value of t_trans_order.ChannelId
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public String getChannelid() {
        return channelid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trans_order.ChannelId
     *
     * @param channelid the value for t_trans_order.ChannelId
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setChannelid(String channelid) {
        this.channelid = channelid == null ? null : channelid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trans_order.Amount
     *
     * @return the value of t_trans_order.Amount
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public Long getAmount() {
        return amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trans_order.Amount
     *
     * @param amount the value for t_trans_order.Amount
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setAmount(Long amount) {
        this.amount = amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trans_order.Currency
     *
     * @return the value of t_trans_order.Currency
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trans_order.Currency
     *
     * @param currency the value for t_trans_order.Currency
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trans_order.Status
     *
     * @return the value of t_trans_order.Status
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trans_order.Status
     *
     * @param status the value for t_trans_order.Status
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trans_order.Result
     *
     * @return the value of t_trans_order.Result
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public Byte getResult() {
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trans_order.Result
     *
     * @param result the value for t_trans_order.Result
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setResult(Byte result) {
        this.result = result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trans_order.ClientIp
     *
     * @return the value of t_trans_order.ClientIp
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public String getClientip() {
        return clientip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trans_order.ClientIp
     *
     * @param clientip the value for t_trans_order.ClientIp
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setClientip(String clientip) {
        this.clientip = clientip == null ? null : clientip.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trans_order.Device
     *
     * @return the value of t_trans_order.Device
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public String getDevice() {
        return device;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trans_order.Device
     *
     * @param device the value for t_trans_order.Device
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setDevice(String device) {
        this.device = device == null ? null : device.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trans_order.RemarkInfo
     *
     * @return the value of t_trans_order.RemarkInfo
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public String getRemarkinfo() {
        return remarkinfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trans_order.RemarkInfo
     *
     * @param remarkinfo the value for t_trans_order.RemarkInfo
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setRemarkinfo(String remarkinfo) {
        this.remarkinfo = remarkinfo == null ? null : remarkinfo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trans_order.ChannelUser
     *
     * @return the value of t_trans_order.ChannelUser
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public String getChanneluser() {
        return channeluser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trans_order.ChannelUser
     *
     * @param channeluser the value for t_trans_order.ChannelUser
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setChanneluser(String channeluser) {
        this.channeluser = channeluser == null ? null : channeluser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trans_order.UserName
     *
     * @return the value of t_trans_order.UserName
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trans_order.UserName
     *
     * @param username the value for t_trans_order.UserName
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trans_order.ChannelMchId
     *
     * @return the value of t_trans_order.ChannelMchId
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public String getChannelmchid() {
        return channelmchid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trans_order.ChannelMchId
     *
     * @param channelmchid the value for t_trans_order.ChannelMchId
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setChannelmchid(String channelmchid) {
        this.channelmchid = channelmchid == null ? null : channelmchid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trans_order.ChannelOrderNo
     *
     * @return the value of t_trans_order.ChannelOrderNo
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public String getChannelorderno() {
        return channelorderno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trans_order.ChannelOrderNo
     *
     * @param channelorderno the value for t_trans_order.ChannelOrderNo
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setChannelorderno(String channelorderno) {
        this.channelorderno = channelorderno == null ? null : channelorderno.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trans_order.ChannelErrCode
     *
     * @return the value of t_trans_order.ChannelErrCode
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public String getChannelerrcode() {
        return channelerrcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trans_order.ChannelErrCode
     *
     * @param channelerrcode the value for t_trans_order.ChannelErrCode
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setChannelerrcode(String channelerrcode) {
        this.channelerrcode = channelerrcode == null ? null : channelerrcode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trans_order.ChannelErrMsg
     *
     * @return the value of t_trans_order.ChannelErrMsg
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public String getChannelerrmsg() {
        return channelerrmsg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trans_order.ChannelErrMsg
     *
     * @param channelerrmsg the value for t_trans_order.ChannelErrMsg
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setChannelerrmsg(String channelerrmsg) {
        this.channelerrmsg = channelerrmsg == null ? null : channelerrmsg.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trans_order.Extra
     *
     * @return the value of t_trans_order.Extra
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public String getExtra() {
        return extra;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trans_order.Extra
     *
     * @param extra the value for t_trans_order.Extra
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setExtra(String extra) {
        this.extra = extra == null ? null : extra.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trans_order.NotifyUrl
     *
     * @return the value of t_trans_order.NotifyUrl
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public String getNotifyurl() {
        return notifyurl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trans_order.NotifyUrl
     *
     * @param notifyurl the value for t_trans_order.NotifyUrl
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setNotifyurl(String notifyurl) {
        this.notifyurl = notifyurl == null ? null : notifyurl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trans_order.Param1
     *
     * @return the value of t_trans_order.Param1
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public String getParam1() {
        return param1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trans_order.Param1
     *
     * @param param1 the value for t_trans_order.Param1
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setParam1(String param1) {
        this.param1 = param1 == null ? null : param1.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trans_order.Param2
     *
     * @return the value of t_trans_order.Param2
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public String getParam2() {
        return param2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trans_order.Param2
     *
     * @param param2 the value for t_trans_order.Param2
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setParam2(String param2) {
        this.param2 = param2 == null ? null : param2.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trans_order.ExpireTime
     *
     * @return the value of t_trans_order.ExpireTime
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public Date getExpiretime() {
        return expiretime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trans_order.ExpireTime
     *
     * @param expiretime the value for t_trans_order.ExpireTime
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setExpiretime(Date expiretime) {
        this.expiretime = expiretime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trans_order.TransSuccTime
     *
     * @return the value of t_trans_order.TransSuccTime
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public Date getTranssucctime() {
        return transsucctime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trans_order.TransSuccTime
     *
     * @param transsucctime the value for t_trans_order.TransSuccTime
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setTranssucctime(Date transsucctime) {
        this.transsucctime = transsucctime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trans_order.CreateTime
     *
     * @return the value of t_trans_order.CreateTime
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trans_order.CreateTime
     *
     * @param createtime the value for t_trans_order.CreateTime
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trans_order.UpdateTime
     *
     * @return the value of t_trans_order.UpdateTime
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trans_order.UpdateTime
     *
     * @param updatetime the value for t_trans_order.UpdateTime
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}