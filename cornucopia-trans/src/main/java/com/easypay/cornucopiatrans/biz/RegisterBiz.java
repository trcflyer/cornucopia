package com.easypay.cornucopiatrans.biz;

import com.easypay.cornucopiatrans.dal.dao.impl.UserInfoMapperImpl;
import com.easypay.cornucopiatrans.dal.dao.impl.UserLoginMapperImpl;
import com.easypay.cornucopiatrans.dal.pojo.UserInfo;
import com.easypay.cornucopiatrans.dal.pojo.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RegisterBiz {

    @Autowired
    private UserInfoMapperImpl userInfoMapper;

    @Autowired
    private UserLoginMapperImpl userLoginMapper;

    public void register(UserLogin userLogin, UserInfo userInfo){
        userInfoMapper.insert(userInfo);
        userLoginMapper.insertSelective(userLogin);
    }
}
