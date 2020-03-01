package com.easypay.cornucopiatrans.dal.dao;

import com.easypay.cornucopiatrans.dal.pojo.UserCardQuick;

public interface UserCardQuickMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserCardQuick record);

    int insertSelective(UserCardQuick record);

    UserCardQuick selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserCardQuick record);

    int updateByPrimaryKey(UserCardQuick record);
}