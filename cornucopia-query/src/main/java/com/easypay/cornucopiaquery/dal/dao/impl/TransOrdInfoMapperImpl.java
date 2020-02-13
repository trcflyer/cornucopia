package com.easypay.cornucopiaquery.dal.dao.impl;

import com.easypay.cornucopiaquery.dal.dao.TransOrdInfoMapper;
import com.easypay.cornucopiaquery.dal.pojo.TransOrdInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface TransOrdInfoMapperImpl extends TransOrdInfoMapper {

    TransOrdInfo selectByUniqueIndex(@Param("transDate") String transDate, @Param("sqeId") String sqeId);

    @Select({"select * from trans_ord_info",
            "where order_id = #{orderId,jdbcType=VARCHAR}"
    }
    )
    @ResultMap("com.easypay.cornucopiaquery.dal.dao.TransOrdInfoMapper.BaseResultMap")
    TransOrdInfo selectByOrderId(@Param("orderId") String orderId);

}
