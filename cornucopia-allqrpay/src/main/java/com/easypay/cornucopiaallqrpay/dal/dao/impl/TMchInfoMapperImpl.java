package com.easypay.cornucopiaallqrpay.dal.dao.impl;

import com.easypay.cornucopiaallqrpay.dal.dao.TMchInfoMapper;
import com.easypay.cornucopiaallqrpay.dal.pojo.TMchInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TMchInfoMapperImpl extends TMchInfoMapper {
    public TMchInfo selectByMchId(@Param("mchId") String mchId);
    public TMchInfo selectByMemberId(@Param("memberId") String memberId);
}
