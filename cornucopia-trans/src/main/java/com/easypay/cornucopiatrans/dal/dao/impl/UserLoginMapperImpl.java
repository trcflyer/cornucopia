package com.easypay.cornucopiatrans.dal.dao.impl;

import com.easypay.cornucopiatrans.dal.dao.UserLoginMapper;
import com.easypay.cornucopiatrans.dal.pojo.UserLogin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginMapperImpl extends UserLoginMapper {

    UserLogin selectByLogin(@Param("loginId") String loginId,@Param("userState") String userState,@Param("loginPswd") String loginPswd);


}
