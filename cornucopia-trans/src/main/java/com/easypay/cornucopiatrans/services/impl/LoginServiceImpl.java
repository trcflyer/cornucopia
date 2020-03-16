package com.easypay.cornucopiatrans.services.impl;

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

        UserLogin login = userLoginMapper.selectByUniqueOpenId(voLogin.getOpenId(),"1");

        if(login != null ){
            resultLogin.setUserId(login.getUserId());
        }

        return null;
    }
}
