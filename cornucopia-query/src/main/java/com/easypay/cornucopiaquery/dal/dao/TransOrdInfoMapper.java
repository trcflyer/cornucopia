package com.easypay.cornucopiaquery.dal.dao;

import com.easypay.cornucopiaquery.dal.pojo.TransOrdInfo;

public interface TransOrdInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TransOrdInfo record);

    int insertSelective(TransOrdInfo record);

    TransOrdInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TransOrdInfo record);

    int updateByPrimaryKey(TransOrdInfo record);
}