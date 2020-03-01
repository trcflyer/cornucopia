package com.easypay.cornucopiaquery.dal.dao;

import com.easypay.cornucopiaquery.dal.pojo.UserCardSettle;

public interface UserCardSettleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserCardSettle record);

    int insertSelective(UserCardSettle record);

    UserCardSettle selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserCardSettle record);

    int updateByPrimaryKey(UserCardSettle record);
}