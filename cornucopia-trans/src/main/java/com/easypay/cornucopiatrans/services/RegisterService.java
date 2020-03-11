package com.easypay.cornucopiatrans.services;

import com.easypay.cornucopiatrans.vo.request.VoRegister;
import com.easypay.cornucopiatrans.vo.response.ResultRegister;

public interface RegisterService {

    public ResultRegister register(VoRegister voLogin,String userFrom);
}
