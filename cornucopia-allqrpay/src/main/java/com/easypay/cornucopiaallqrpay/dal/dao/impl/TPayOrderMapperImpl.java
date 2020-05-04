package com.easypay.cornucopiaallqrpay.dal.dao.impl;

import com.easypay.cornucopiaallqrpay.dal.dao.TPayOrderMapper;
import com.easypay.cornucopiaallqrpay.dal.pojo.TPayOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TPayOrderMapperImpl extends TPayOrderMapper {

    TPayOrder selectByPayOrderId(@Param("payOrderId") String payOrderId);

    TPayOrder selectMchOrderId(@Param("mchId") String mchId,@Param("mchOrderId") String mchOrderId);

}
