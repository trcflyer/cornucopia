package com.easypay.cornucopiatrans.biz;

import com.easypay.cornucopiatrans.dal.dao.UserWechatOpenidMapper;
import com.easypay.cornucopiatrans.dal.dao.impl.UserInfoMapperImpl;
import com.easypay.cornucopiatrans.dal.dao.impl.UserLoginMapperImpl;
import com.easypay.cornucopiatrans.dal.dao.impl.UserWechatOpenidMapperImpl;
import com.easypay.cornucopiatrans.dal.pojo.UserInfo;
import com.easypay.cornucopiatrans.dal.pojo.UserLogin;
import com.easypay.cornucopiatrans.dal.pojo.UserWechatOpenid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class RegisterBiz {

    @Autowired
    private UserInfoMapperImpl userInfoMapper;

    @Autowired
    private UserLoginMapperImpl userLoginMapper;

    @Autowired
    private UserWechatOpenidMapperImpl userWechatOpenidMapper;

    @Transactional
    public void register(UserLogin userLogin, UserInfo userInfo, UserWechatOpenid userWechatOpenid){
        userInfoMapper.insert(userInfo);
        userLoginMapper.insertSelective(userLogin);
        userWechatOpenidMapper.insertSelective(userWechatOpenid);
    }
}
