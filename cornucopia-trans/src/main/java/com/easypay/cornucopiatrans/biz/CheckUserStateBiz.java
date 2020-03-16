package com.easypay.cornucopiatrans.biz;

import com.easypay.cornucopiacommon.enums.RespCode;
import com.easypay.cornucopiatrans.dal.dao.UserInfoMapper;
import com.easypay.cornucopiatrans.dal.dao.impl.UserInfoMapperImpl;
import com.easypay.cornucopiatrans.dal.dao.impl.UserLoginMapperImpl;
import com.easypay.cornucopiatrans.dal.pojo.UserInfo;
import com.easypay.cornucopiatrans.dal.pojo.UserLogin;
import com.easypay.cornucopiatrans.vo.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CheckUserStateBiz {

    @Autowired
    private UserInfoMapperImpl userInfoMapper;

    @Autowired
    private UserLoginMapperImpl userLoginMapper;

    public BaseResponse checkUser(String userId){
        BaseResponse baseResponse = new BaseResponse();

        UserInfo userInfo = userInfoMapper.selectByUniqueIndex(userId);
        UserLogin userLogin = userLoginMapper.selectByUserId(userId);
        if(userInfo == null || userLogin == null){
            baseResponse.setRespCode(RespCode.CODE_100.getRespCode());
            baseResponse.setRespDesc(RespCode.CODE_100.getRespDesc());
            return baseResponse;
        }

        if("2".equals(userLogin.getUserState())){
            baseResponse.setRespCode(RespCode.CODE_101.getRespCode());
            baseResponse.setRespDesc(RespCode.CODE_101.getRespDesc());
            return baseResponse;
        }

        return baseResponse;
    }
}
