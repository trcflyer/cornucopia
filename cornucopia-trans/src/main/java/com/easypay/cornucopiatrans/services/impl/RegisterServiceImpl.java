package com.easypay.cornucopiatrans.services.impl;

import cn.hutool.core.util.StrUtil;
import com.easypay.cornucopiacommon.bean.EncryptResult;
import com.easypay.cornucopiacommon.enums.RespCode;
import com.easypay.cornucopiacommon.enums.UserLevel;
import com.easypay.cornucopiacommon.utils.EncryptUtil;
import com.easypay.cornucopiatrans.biz.TransactionalBiz;
import com.easypay.cornucopiatrans.biz.SequenceBiz;
import com.easypay.cornucopiatrans.dal.dao.impl.UserLoginMapperImpl;
import com.easypay.cornucopiatrans.dal.pojo.UserInfo;
import com.easypay.cornucopiatrans.dal.pojo.UserLogin;
import com.easypay.cornucopiatrans.dal.pojo.UserMap;
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
    private TransactionalBiz transactionalBiz;

    @Autowired
    private SequenceBiz sequenceBiz;

    @Autowired
    private UserLoginMapperImpl userLoginMapper;

    @Autowired
    private EncryptUtil encryptUtil;

    @Override
    public ResultRegister register(VoRegister voRegister,String userFrom) {
        ResultRegister resultRegister = new ResultRegister();

        EncryptResult encryptResult = encryptUtil.encrypt(voRegister.getLoginId());

        UserLogin userLogin = userLoginMapper.selectByLogin(encryptResult.getCipherText(),"1");
        if(userLogin != null){
            resultRegister.setRespCode(RespCode.CODE_003.getRespCode());
            resultRegister.setRespDesc(RespCode.CODE_003.getRespDesc());
            return resultRegister;
        }

        String userId = generateUserId();
        if(StringUtils.isEmpty(userId)){
            log.error("生成商户号失败");
            return null;
        }

        userLogin = new UserLogin();
        initUserLogin(userLogin,userId,encryptResult,voRegister.getOpenId(),userFrom);

        UserInfo userInfo = new UserInfo();
        initUserInfo(userInfo,userId);

        UserMap userMap = new UserMap();
        initUserMap(userMap,userId);

        transactionalBiz.register(userLogin,userInfo,userMap);

        resultRegister.setUserId(userId);
        resultRegister.setRespCode(RespCode.CODE_000.getRespCode());
        resultRegister.setRespDesc(RespCode.CODE_000.getRespDesc());
        return resultRegister;
    }

    private void initUserInfo(UserInfo userInfo,String userId){
        userInfo.setUserId(userId);
        userInfo.setUserLevel(UserLevel.ONE.getUserLevel());

    }
    private void initUserMap(UserMap userMap,String userId){
        userMap.setUserId(userId);

    }
    private void initUserLogin(UserLogin userLogin,String userId,EncryptResult encryptResult,String openId,String from){
        userLogin.setUserId(userId);
        userLogin.setLoginId(encryptResult.getCipherText());
        userLogin.setLoginIdMask(encryptResult.getMaskText());
        userLogin.setOpenId(openId);
        userLogin.setUserState(1);
        userLogin.setUserFrom(Integer.valueOf(from));
    }

    private String generateUserId(){
        String userId = null;
        try {
            userId = sequenceBiz.getSeqId("USER_ID_SQE");
            userId = StrUtil.fillBefore(userId,'0',10);
            userId = "100000"+userId;
        }catch (Exception e){
            log.error("生成用户id失败");
            return null;
        }
        return userId;
    }

}
