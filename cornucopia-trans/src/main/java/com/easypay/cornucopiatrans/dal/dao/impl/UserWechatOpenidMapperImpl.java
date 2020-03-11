package com.easypay.cornucopiatrans.dal.dao.impl;

import com.easypay.cornucopiatrans.dal.dao.UserWechatOpenidMapper;
import com.easypay.cornucopiatrans.dal.pojo.UserWechatOpenid;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWechatOpenidMapperImpl extends UserWechatOpenidMapper {
    UserWechatOpenid selectByUniqueOpenId(@Param("openId") String openId);
}
