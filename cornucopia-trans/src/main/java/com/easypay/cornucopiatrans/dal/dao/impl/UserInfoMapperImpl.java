package com.easypay.cornucopiatrans.dal.dao.impl;

import com.easypay.cornucopiatrans.dal.dao.UserInfoMapper;
import com.easypay.cornucopiatrans.dal.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoMapperImpl extends UserInfoMapper {

    UserInfo selectByUniqueIndex(@Param("userId") String userId);

}
