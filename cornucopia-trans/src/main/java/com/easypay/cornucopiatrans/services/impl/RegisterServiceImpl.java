package com.easypay.cornucopiatrans.services.impl;

import com.easypay.cornucopiacommon.utils.MD5Util;
import com.easypay.cornucopiatrans.biz.RegisterBiz;
import com.easypay.cornucopiatrans.dal.pojo.UserInfo;
import com.easypay.cornucopiatrans.dal.pojo.UserLogin;
import com.easypay.cornucopiatrans.services.RegisterService;
import com.easypay.cornucopiatrans.vo.request.VoRegister;
import com.easypay.cornucopiatrans.vo.response.ResultRegister;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisterBiz registerBiz;

    @Override
    public ResultRegister register(VoRegister voRegister) {
        ResultRegister resultRegister = new ResultRegister();
        String userId = generateUserId();
        if(StringUtils.isEmpty(userId)){
            log.error("生成商户号失败");
            return null;
        }

        String pwd = MD5Util.string2MD5(voRegister.getLoginPswd());

        UserLogin userLogin = new UserLogin();
        initUserLogin(userLogin,userId,voRegister.getLoginId(),pwd,voRegister.getUserFrom());

        UserInfo userInfo = new UserInfo();
        initUserInfo(userInfo,userId);

        registerBiz.register(userLogin,userInfo);

        resultRegister.setUserId(userId);
        return resultRegister;
    }

    private void initUserInfo(UserInfo userInfo,String userId){
        userInfo.setUserId(userId);

    }
    private void initUserLogin(UserLogin userLogin,String userId,String loginId,String pwd ,String from){
        userLogin.setUserId(userId);
        userLogin.setLoginId(loginId);
        userLogin.setLoginPswd(pwd);
        userLogin.setUserState(1);
        userLogin.setPswdFailCount(0);
        userLogin.setUserFrom(from);
    }

    private String generateUserId(){
        String userId = null;

        return userId;
    }
}
