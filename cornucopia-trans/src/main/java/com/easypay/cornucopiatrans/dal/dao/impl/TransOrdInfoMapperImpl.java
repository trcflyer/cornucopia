package com.easypay.cornucopiatrans.dal.dao.impl;

import com.easypay.cornucopiatrans.dal.dao.TransOrdInfoMapper;
import com.easypay.cornucopiatrans.dal.pojo.TransOrdInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface TransOrdInfoMapperImpl extends TransOrdInfoMapper {

    TransOrdInfo selectByUniqueIndex(@Param("transDate") String transDate,@Param("sqeId") String sqeId);

    @Select({"select * from trans_ord_info",
            "where order_id = #{orderId,jdbcType=VARCHAR}"
    }
    )
    @ResultMap("com.easypay.cornucopiatrans.dal.dao.TransOrdInfoMapper.BaseResultMap")
    TransOrdInfo selectByOrderId(@Param("orderId") String orderId);


    @Select({"select * from trans_ord_info",
            "where user_Id = #{userId,jdbcType=VARCHAR} order by create_time desc limit 100"
    }
    )
    @ResultMap("com.easypay.cornucopiatrans.dal.dao.TransOrdInfoMapper.BaseResultMap")
    ArrayList<TransOrdInfo> selectOrderListByUserId(@Param("userId") String userId);

}
