package com.easypay.cornucopiatrans.dal.dao.impl;

import com.easypay.cornucopiatrans.dal.dao.UserCardSettleMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCardSettleMapperImpl extends UserCardSettleMapper {

   int updateCardStateByUserId(@Param("userId") String userId);
}
