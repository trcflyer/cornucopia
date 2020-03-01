package com.easypay.cornucopiaquery.dal.dao;

import com.easypay.cornucopiaquery.dal.pojo.UserCardQuick;

public interface UserCardQuickMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserCardQuick record);

    int insertSelective(UserCardQuick record);

    UserCardQuick selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserCardQuick record);

    int updateByPrimaryKey(UserCardQuick record);
}