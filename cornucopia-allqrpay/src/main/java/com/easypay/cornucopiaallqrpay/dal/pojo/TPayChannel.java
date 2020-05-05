package com.easypay.cornucopiaallqrpay.dal.pojo;

import java.util.Date;

public class TPayChannel {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_channel.id
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_channel.ChannelId
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    private String channelid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_channel.ChannelName
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    private String channelname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_channel.ChannelMchId
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    private String channelmchid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_channel.MchId
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    private String mchid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_channel.State
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    private Byte state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_channel.Param
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    private String param;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_channel.Remark
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_channel.CreateTime
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_channel.UpdateTime
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    private Date updatetime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_channel.id
     *
     * @return the value of t_pay_channel.id
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_channel.id
     *
     * @param id the value for t_pay_channel.id
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_channel.ChannelId
     *
     * @return the value of t_pay_channel.ChannelId
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    public String getChannelid() {
        return channelid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_channel.ChannelId
     *
     * @param channelid the value for t_pay_channel.ChannelId
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    public void setChannelid(String channelid) {
        this.channelid = channelid == null ? null : channelid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_channel.ChannelName
     *
     * @return the value of t_pay_channel.ChannelName
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    public String getChannelname() {
        return channelname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_channel.ChannelName
     *
     * @param channelname the value for t_pay_channel.ChannelName
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    public void setChannelname(String channelname) {
        this.channelname = channelname == null ? null : channelname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_channel.ChannelMchId
     *
     * @return the value of t_pay_channel.ChannelMchId
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    public String getChannelmchid() {
        return channelmchid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_channel.ChannelMchId
     *
     * @param channelmchid the value for t_pay_channel.ChannelMchId
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    public void setChannelmchid(String channelmchid) {
        this.channelmchid = channelmchid == null ? null : channelmchid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_channel.MchId
     *
     * @return the value of t_pay_channel.MchId
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    public String getMchid() {
        return mchid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_channel.MchId
     *
     * @param mchid the value for t_pay_channel.MchId
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    public void setMchid(String mchid) {
        this.mchid = mchid == null ? null : mchid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_channel.State
     *
     * @return the value of t_pay_channel.State
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    public Byte getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_channel.State
     *
     * @param state the value for t_pay_channel.State
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    public void setState(Byte state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_channel.Param
     *
     * @return the value of t_pay_channel.Param
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    public String getParam() {
        return param;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_channel.Param
     *
     * @param param the value for t_pay_channel.Param
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    public void setParam(String param) {
        this.param = param == null ? null : param.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_channel.Remark
     *
     * @return the value of t_pay_channel.Remark
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_channel.Remark
     *
     * @param remark the value for t_pay_channel.Remark
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_channel.CreateTime
     *
     * @return the value of t_pay_channel.CreateTime
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_channel.CreateTime
     *
     * @param createtime the value for t_pay_channel.CreateTime
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_channel.UpdateTime
     *
     * @return the value of t_pay_channel.UpdateTime
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_channel.UpdateTime
     *
     * @param updatetime the value for t_pay_channel.UpdateTime
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}