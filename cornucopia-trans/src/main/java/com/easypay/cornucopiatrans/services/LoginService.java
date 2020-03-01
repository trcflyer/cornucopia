package com.easypay.cornucopiatrans.services;

import com.easypay.cornucopiatrans.vo.request.VoLogin;
import com.easypay.cornucopiatrans.vo.response.ResultLogin;

public interface LoginService {
    public ResultLogin login(VoLogin voLogin);
}
