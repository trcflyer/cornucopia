package com.easypay.cornucopiatrans.dal.dao;

import com.easypay.cornucopiatrans.dal.pojo.Sequence;

public interface SequenceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Sequence record);

    int insertSelective(Sequence record);

    Sequence selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Sequence record);

    int updateByPrimaryKey(Sequence record);
}