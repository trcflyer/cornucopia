package com.easypay.cornucopiatrans.services.impl;

import com.easypay.cornucopiacommon.enums.RespCode;
import com.easypay.cornucopiacommon.enums.UserLevel;
import com.easypay.cornucopiatrans.biz.CheckNameBiz;
import com.easypay.cornucopiatrans.biz.CheckPhotoBiz;
import com.easypay.cornucopiatrans.dal.dao.impl.UserInfoMapperImpl;
import com.easypay.cornucopiatrans.dal.pojo.UserInfo;
import com.easypay.cornucopiatrans.services.CheckRealService;
import com.easypay.cornucopiatrans.vo.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CheckRealServiceImpl implements CheckRealService {

    @Autowired
    private UserInfoMapperImpl userInfoMapper;

    @Autowired
    private CheckNameBiz checkNameBiz;

    @Autowired
    private CheckPhotoBiz checkPhotoBiz;

    /**
     * 调用外部系统进行实名认证
     * @param idno
     * @param legelName
     * @return
     */
    @Override
    public BaseResponse checkName(String userId,String idno,String legelName){
        BaseResponse baseResponse = new BaseResponse();
        UserInfo userInfo = userInfoMapper.selectByUniqueIndex(userId);
        if(userInfo.getUserLevel() > UserLevel.ONE.getUserLevel()){
            log.info("已经进行过实名认证");
            baseResponse.setRespCode(RespCode.CODE_000.getRespCode());
            baseResponse.setRespDesc(RespCode.CODE_000.getRespCode());
            return baseResponse;
        }

        checkNameBiz.ckeck();

        UserInfo record = new UserInfo();
        record.setId(userInfo.getId());
        record.setIdNo(idno);
        record.setUserLevel(UserLevel.TWO.getUserLevel());
        record.setLegelName(legelName);
        userInfoMapper.updateByPrimaryKeySelective(userInfo);

        baseResponse.setRespCode(RespCode.CODE_000.getRespCode());
        baseResponse.setRespDesc(RespCode.CODE_000.getRespCode());
        return baseResponse;
    }
    /**
     * 调用外部系统进行身份证照片认证
     * @return
     */
    @Override
    public BaseResponse checkPhoto(String userId,String photoF,String photoB){
        BaseResponse baseResponse = new BaseResponse();
        UserInfo userInfo = userInfoMapper.selectByUniqueIndex(userId);
        if(userInfo.getUserLevel() > UserLevel.TWO.getUserLevel()){
            log.info("已经进行过身份证照片认证");
            baseResponse.setRespCode(RespCode.CODE_000.getRespCode());
            baseResponse.setRespDesc(RespCode.CODE_000.getRespCode());
            return baseResponse;
        }

        checkPhotoBiz.ckeck();

        UserInfo record = new UserInfo();
        record.setId(userInfo.getId());
        record.setIdPhotoB(photoF);
        record.setUserLevel(UserLevel.THREE.getUserLevel());
        record.setIdPhotoF(photoB);
        userInfoMapper.updateByPrimaryKeySelective(userInfo);

        baseResponse.setRespCode(RespCode.CODE_000.getRespCode());
        baseResponse.setRespDesc(RespCode.CODE_000.getRespCode());
        return baseResponse;
    }

}
