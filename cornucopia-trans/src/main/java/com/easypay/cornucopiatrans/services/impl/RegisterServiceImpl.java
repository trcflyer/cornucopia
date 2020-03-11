package com.easypay.cornucopiatrans.services.impl;

import com.easypay.cornucopiacommon.enums.RespCode;
import com.easypay.cornucopiacommon.utils.MD5Util;
import com.easypay.cornucopiatrans.biz.RegisterBiz;
import com.easypay.cornucopiatrans.biz.SequenceBiz;
import com.easypay.cornucopiatrans.dal.dao.impl.UserLoginMapperImpl;
import com.easypay.cornucopiatrans.dal.pojo.UserInfo;
import com.easypay.cornucopiatrans.dal.pojo.UserLogin;
import com.easypay.cornucopiatrans.dal.pojo.UserWechatOpenid;
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

    @Autowired
    private SequenceBiz sequenceBiz;

    @Autowired
    private UserLoginMapperImpl userLoginMapper;

    @Override
    public ResultRegister register(VoRegister voRegister,String userFrom) {
        ResultRegister resultRegister = new ResultRegister();

        UserLogin userLogin = userLoginMapper.selectByLogin(voRegister.getLoginId(),"1");
        if(userLogin != null){
            resultRegister.setRespCode(RespCode.CODE_003.getRespCode());
            resultRegister.setRespDesc(RespCode.CODE_003.getRespCode());
            return resultRegister;
        }

        String userId = generateUserId();
        if(StringUtils.isEmpty(userId)){
            log.error("生成商户号失败");
            return null;
        }

        userLogin = new UserLogin();
        initUserLogin(userLogin,userId,voRegister.getLoginId(),userFrom);

        UserInfo userInfo = new UserInfo();
        initUserInfo(userInfo,userId);

        UserWechatOpenid userWechatOpenid = new UserWechatOpenid();
        initUserWechatOpenid(userWechatOpenid,userId,voRegister.getOpenId());

        registerBiz.register(userLogin,userInfo,userWechatOpenid);

        resultRegister.setUserId(userId);
        resultRegister.setRespCode(RespCode.CODE_000.getRespCode());
        resultRegister.setRespDesc(RespCode.CODE_000.getRespCode());
        return resultRegister;
    }

    private void initUserInfo(UserInfo userInfo,String userId){
        userInfo.setUserId(userId);

    }
    private void initUserLogin(UserLogin userLogin,String userId,String loginId,String from){
        userLogin.setUserId(userId);
        userLogin.setLoginId(loginId);
        userLogin.setUserState(1);
        userLogin.setUserFrom(Integer.valueOf(from));
    }

    private void initUserWechatOpenid(UserWechatOpenid userWechatOpenid,String userId,String openId){
        userWechatOpenid.setOpenId(openId);
        userWechatOpenid.setUserId(userId);
    }

    private String generateUserId(){
        String userId = null;
        try {
            userId = sequenceBiz.getSeqId("USER_ID_SQE");
        }catch (Exception e){
            log.error("生成用户id失败");
            return null;
        }
        return userId;
    }

}
