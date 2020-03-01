package com.easypay.cornucopiatrans.dal.dao;

import com.easypay.cornucopiatrans.dal.pojo.TransOrdInfo;

public interface TransOrdInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TransOrdInfo record);

    int insertSelective(TransOrdInfo record);

    TransOrdInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TransOrdInfo record);

    int updateByPrimaryKey(TransOrdInfo record);
}