package com.easypay.cornucopiaallqrpay.dal.pojo;

import java.util.Date;

public class TMchInfo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_mch_info.id
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_mch_info.Mch_Id
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    private String mchId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_mch_info.Name
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_mch_info.Type
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_mch_info.State
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    private Byte state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_mch_info.random_Key
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    private String randomKey;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_mch_info.CreateTime
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_mch_info.UpdateTime
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    private Date updatetime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_mch_info.id
     *
     * @return the value of t_mch_info.id
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_mch_info.id
     *
     * @param id the value for t_mch_info.id
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_mch_info.Mch_Id
     *
     * @return the value of t_mch_info.Mch_Id
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    public String getMchId() {
        return mchId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_mch_info.Mch_Id
     *
     * @param mchId the value for t_mch_info.Mch_Id
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    public void setMchId(String mchId) {
        this.mchId = mchId == null ? null : mchId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_mch_info.Name
     *
     * @return the value of t_mch_info.Name
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_mch_info.Name
     *
     * @param name the value for t_mch_info.Name
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_mch_info.Type
     *
     * @return the value of t_mch_info.Type
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_mch_info.Type
     *
     * @param type the value for t_mch_info.Type
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_mch_info.State
     *
     * @return the value of t_mch_info.State
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    public Byte getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_mch_info.State
     *
     * @param state the value for t_mch_info.State
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    public void setState(Byte state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_mch_info.random_Key
     *
     * @return the value of t_mch_info.random_Key
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    public String getRandomKey() {
        return randomKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_mch_info.random_Key
     *
     * @param randomKey the value for t_mch_info.random_Key
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    public void setRandomKey(String randomKey) {
        this.randomKey = randomKey == null ? null : randomKey.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_mch_info.CreateTime
     *
     * @return the value of t_mch_info.CreateTime
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_mch_info.CreateTime
     *
     * @param createtime the value for t_mch_info.CreateTime
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_mch_info.UpdateTime
     *
     * @return the value of t_mch_info.UpdateTime
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_mch_info.UpdateTime
     *
     * @param updatetime the value for t_mch_info.UpdateTime
     *
     * @mbggenerated Tue May 05 14:54:12 CST 2020
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}