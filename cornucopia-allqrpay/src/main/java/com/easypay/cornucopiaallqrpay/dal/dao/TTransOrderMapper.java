package com.easypay.cornucopiaallqrpay.dal.dao;

import com.easypay.cornucopiaallqrpay.dal.pojo.TTransOrder;

public interface TTransOrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trans_order
     *
     * @mbggenerated Mon May 04 17:04:08 CST 2020
     */
    int deleteByPrimaryKey(String transorderid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trans_order
     *
     * @mbggenerated Mon May 04 17:04:08 CST 2020
     */
    int insert(TTransOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trans_order
     *
     * @mbggenerated Mon May 04 17:04:08 CST 2020
     */
    int insertSelective(TTransOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trans_order
     *
     * @mbggenerated Mon May 04 17:04:08 CST 2020
     */
    TTransOrder selectByPrimaryKey(String transorderid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trans_order
     *
     * @mbggenerated Mon May 04 17:04:08 CST 2020
     */
    int updateByPrimaryKeySelective(TTransOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trans_order
     *
     * @mbggenerated Mon May 04 17:04:08 CST 2020
     */
    int updateByPrimaryKey(TTransOrder record);
}