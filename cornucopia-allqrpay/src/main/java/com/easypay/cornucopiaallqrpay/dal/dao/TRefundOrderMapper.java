package com.easypay.cornucopiaallqrpay.dal.dao;

import com.easypay.cornucopiaallqrpay.dal.pojo.TRefundOrder;

public interface TRefundOrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_refund_order
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    int deleteByPrimaryKey(String refundorderid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_refund_order
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    int insert(TRefundOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_refund_order
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    int insertSelective(TRefundOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_refund_order
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    TRefundOrder selectByPrimaryKey(String refundorderid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_refund_order
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    int updateByPrimaryKeySelective(TRefundOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_refund_order
     *
     * @mbggenerated Sun Apr 05 13:41:50 CST 2020
     */
    int updateByPrimaryKey(TRefundOrder record);
}