package com.easypay.cornucopiaallqrpay.dal.pojo;

import java.util.Date;

public class Sequence {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sequence.id
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sequence.seq_name
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    private String seqName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sequence.next_value
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    private Long nextValue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sequence.increment_by
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    private Integer incrementBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sequence.min_value
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    private Integer minValue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sequence.max_value
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    private Long maxValue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sequence.version
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    private Integer version;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sequence.create_time
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sequence.update_time
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sequence.id
     *
     * @return the value of sequence.id
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sequence.id
     *
     * @param id the value for sequence.id
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sequence.seq_name
     *
     * @return the value of sequence.seq_name
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    public String getSeqName() {
        return seqName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sequence.seq_name
     *
     * @param seqName the value for sequence.seq_name
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    public void setSeqName(String seqName) {
        this.seqName = seqName == null ? null : seqName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sequence.next_value
     *
     * @return the value of sequence.next_value
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    public Long getNextValue() {
        return nextValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sequence.next_value
     *
     * @param nextValue the value for sequence.next_value
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    public void setNextValue(Long nextValue) {
        this.nextValue = nextValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sequence.increment_by
     *
     * @return the value of sequence.increment_by
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    public Integer getIncrementBy() {
        return incrementBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sequence.increment_by
     *
     * @param incrementBy the value for sequence.increment_by
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    public void setIncrementBy(Integer incrementBy) {
        this.incrementBy = incrementBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sequence.min_value
     *
     * @return the value of sequence.min_value
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    public Integer getMinValue() {
        return minValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sequence.min_value
     *
     * @param minValue the value for sequence.min_value
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    public void setMinValue(Integer minValue) {
        this.minValue = minValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sequence.max_value
     *
     * @return the value of sequence.max_value
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    public Long getMaxValue() {
        return maxValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sequence.max_value
     *
     * @param maxValue the value for sequence.max_value
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    public void setMaxValue(Long maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sequence.version
     *
     * @return the value of sequence.version
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sequence.version
     *
     * @param version the value for sequence.version
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sequence.create_time
     *
     * @return the value of sequence.create_time
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sequence.create_time
     *
     * @param createTime the value for sequence.create_time
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sequence.update_time
     *
     * @return the value of sequence.update_time
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sequence.update_time
     *
     * @param updateTime the value for sequence.update_time
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}