package com.easypay.cornucopiatrans.dal.dao;

import com.easypay.cornucopiatrans.dal.pojo.UserCardSettle;

public interface UserCardSettleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserCardSettle record);

    int insertSelective(UserCardSettle record);

    UserCardSettle selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserCardSettle record);

    int updateByPrimaryKey(UserCardSettle record);
}