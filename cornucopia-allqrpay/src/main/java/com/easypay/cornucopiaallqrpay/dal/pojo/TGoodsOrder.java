package com.easypay.cornucopiaallqrpay.dal.pojo;

import java.util.Date;

public class TGoodsOrder {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods_order.GoodsOrderId
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private String goodsorderid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods_order.GoodsId
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private String goodsid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods_order.GoodsName
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private String goodsname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods_order.Amount
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private Long amount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods_order.UserId
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private String userid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods_order.Status
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private Byte status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods_order.PayOrderId
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private String payorderid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods_order.ChannelId
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private String channelid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods_order.ChannelUserId
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private String channeluserid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods_order.CreateTime
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods_order.UpdateTime
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    private Date updatetime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods_order.GoodsOrderId
     *
     * @return the value of t_goods_order.GoodsOrderId
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public String getGoodsorderid() {
        return goodsorderid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods_order.GoodsOrderId
     *
     * @param goodsorderid the value for t_goods_order.GoodsOrderId
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setGoodsorderid(String goodsorderid) {
        this.goodsorderid = goodsorderid == null ? null : goodsorderid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods_order.GoodsId
     *
     * @return the value of t_goods_order.GoodsId
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public String getGoodsid() {
        return goodsid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods_order.GoodsId
     *
     * @param goodsid the value for t_goods_order.GoodsId
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid == null ? null : goodsid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods_order.GoodsName
     *
     * @return the value of t_goods_order.GoodsName
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public String getGoodsname() {
        return goodsname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods_order.GoodsName
     *
     * @param goodsname the value for t_goods_order.GoodsName
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname == null ? null : goodsname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods_order.Amount
     *
     * @return the value of t_goods_order.Amount
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public Long getAmount() {
        return amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods_order.Amount
     *
     * @param amount the value for t_goods_order.Amount
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setAmount(Long amount) {
        this.amount = amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods_order.UserId
     *
     * @return the value of t_goods_order.UserId
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public String getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods_order.UserId
     *
     * @param userid the value for t_goods_order.UserId
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods_order.Status
     *
     * @return the value of t_goods_order.Status
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods_order.Status
     *
     * @param status the value for t_goods_order.Status
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods_order.PayOrderId
     *
     * @return the value of t_goods_order.PayOrderId
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public String getPayorderid() {
        return payorderid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods_order.PayOrderId
     *
     * @param payorderid the value for t_goods_order.PayOrderId
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setPayorderid(String payorderid) {
        this.payorderid = payorderid == null ? null : payorderid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods_order.ChannelId
     *
     * @return the value of t_goods_order.ChannelId
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public String getChannelid() {
        return channelid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods_order.ChannelId
     *
     * @param channelid the value for t_goods_order.ChannelId
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setChannelid(String channelid) {
        this.channelid = channelid == null ? null : channelid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods_order.ChannelUserId
     *
     * @return the value of t_goods_order.ChannelUserId
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public String getChanneluserid() {
        return channeluserid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods_order.ChannelUserId
     *
     * @param channeluserid the value for t_goods_order.ChannelUserId
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setChanneluserid(String channeluserid) {
        this.channeluserid = channeluserid == null ? null : channeluserid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods_order.CreateTime
     *
     * @return the value of t_goods_order.CreateTime
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods_order.CreateTime
     *
     * @param createtime the value for t_goods_order.CreateTime
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods_order.UpdateTime
     *
     * @return the value of t_goods_order.UpdateTime
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods_order.UpdateTime
     *
     * @param updatetime the value for t_goods_order.UpdateTime
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}