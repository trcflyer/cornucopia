package com.easypay.cornucopiaallqrpay.dal.dao;

import com.easypay.cornucopiaallqrpay.dal.pojo.UserCardQuick;

public interface UserCardQuickMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_card_quick
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_card_quick
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    int insert(UserCardQuick record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_card_quick
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    int insertSelective(UserCardQuick record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_card_quick
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    UserCardQuick selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_card_quick
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    int updateByPrimaryKeySelective(UserCardQuick record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_card_quick
     *
     * @mbggenerated Sun Mar 22 20:21:27 CST 2020
     */
    int updateByPrimaryKey(UserCardQuick record);
}