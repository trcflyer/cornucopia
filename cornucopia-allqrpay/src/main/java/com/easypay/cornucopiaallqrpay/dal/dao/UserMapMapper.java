package com.easypay.cornucopiaallqrpay.dal.dao;

import com.easypay.cornucopiaallqrpay.dal.pojo.UserMap;

public interface UserMapMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_map
     *
     * @mbggenerated Sat Apr 11 14:55:49 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_map
     *
     * @mbggenerated Sat Apr 11 14:55:49 CST 2020
     */
    int insert(UserMap record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_map
     *
     * @mbggenerated Sat Apr 11 14:55:49 CST 2020
     */
    int insertSelective(UserMap record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_map
     *
     * @mbggenerated Sat Apr 11 14:55:49 CST 2020
     */
    UserMap selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_map
     *
     * @mbggenerated Sat Apr 11 14:55:49 CST 2020
     */
    int updateByPrimaryKeySelective(UserMap record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_map
     *
     * @mbggenerated Sat Apr 11 14:55:49 CST 2020
     */
    int updateByPrimaryKey(UserMap record);
}