package com.easypay.cornucopiaallqrpay.dal.dao;

import com.easypay.cornucopiaallqrpay.dal.pojo.TMchNotify;

public interface TMchNotifyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mch_notify
     *
     * @mbggenerated Wed Apr 22 21:39:18 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mch_notify
     *
     * @mbggenerated Wed Apr 22 21:39:18 CST 2020
     */
    int insert(TMchNotify record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mch_notify
     *
     * @mbggenerated Wed Apr 22 21:39:18 CST 2020
     */
    int insertSelective(TMchNotify record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mch_notify
     *
     * @mbggenerated Wed Apr 22 21:39:18 CST 2020
     */
    TMchNotify selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mch_notify
     *
     * @mbggenerated Wed Apr 22 21:39:18 CST 2020
     */
    int updateByPrimaryKeySelective(TMchNotify record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mch_notify
     *
     * @mbggenerated Wed Apr 22 21:39:18 CST 2020
     */
    int updateByPrimaryKey(TMchNotify record);
}