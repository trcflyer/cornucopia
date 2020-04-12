package com.easypay.cornucopiaallqrpay.dal.dao.impl;

import com.easypay.cornucopiaallqrpay.dal.dao.TGoodsOrderMapper;
import com.easypay.cornucopiaallqrpay.dal.pojo.TGoodsOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TGoodsOrderMapperImpl extends TGoodsOrderMapper {
    TGoodsOrder selectByGoodsOrderId(@Param("goodsOrderId") String goodsOrderId);

}
