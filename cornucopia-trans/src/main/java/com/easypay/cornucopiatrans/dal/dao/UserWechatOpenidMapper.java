package com.easypay.cornucopiatrans.dal.dao;

import com.easypay.cornucopiatrans.dal.pojo.UserWechatOpenid;

public interface UserWechatOpenidMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserWechatOpenid record);

    int insertSelective(UserWechatOpenid record);

    UserWechatOpenid selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserWechatOpenid record);

    int updateByPrimaryKey(UserWechatOpenid record);
}