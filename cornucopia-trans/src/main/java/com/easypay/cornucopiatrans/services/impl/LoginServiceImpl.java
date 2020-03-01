package com.easypay.cornucopiatrans.services.impl;

import com.easypay.cornucopiatrans.services.LoginService;
import com.easypay.cornucopiatrans.vo.request.VoLogin;
import com.easypay.cornucopiatrans.vo.response.ResultLogin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public ResultLogin login(VoLogin voLogin) {
        ResultLogin  resultLogin = new ResultLogin();

        return resultLogin;
    }
}
