package com.easypay.cornucopiatrans.services.impl;

import com.easypay.cornucopiacommon.utils.MD5Util;
import com.easypay.cornucopiatrans.dal.dao.impl.UserInfoMapperImpl;
import com.easypay.cornucopiatrans.dal.dao.impl.UserLoginMapperImpl;
import com.easypay.cornucopiatrans.dal.pojo.UserLogin;
import com.easypay.cornucopiatrans.services.LoginService;
import com.easypay.cornucopiatrans.vo.request.VoLogin;
import com.easypay.cornucopiatrans.vo.response.ResultLogin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserLoginMapperImpl userLoginMapper;

    @Override
    public ResultLogin login(VoLogin voLogin) {
        ResultLogin  resultLogin = new ResultLogin();

        String pwd = MD5Util.string2MD5(voLogin.getLoginPswd());
        UserLogin userLogin = userLoginMapper.selectByLogin(voLogin.getLoginId(),"1", voLogin.getLoginPswd());
        if(userLogin != null){
            resultLogin.setUserId(userLogin.getUserId());
        }

        return null;
    }
}
