package com.easypay.cornucopiaallqrpay.dal.dao;

import com.easypay.cornucopiaallqrpay.dal.pojo.TransOrdInfo;

public interface TransOrdInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trans_ord_info
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trans_ord_info
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    int insert(TransOrdInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trans_ord_info
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    int insertSelective(TransOrdInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trans_ord_info
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    TransOrdInfo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trans_ord_info
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    int updateByPrimaryKeySelective(TransOrdInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trans_ord_info
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    int updateByPrimaryKey(TransOrdInfo record);
}