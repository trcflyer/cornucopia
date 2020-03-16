package com.easypay.cornucopiatrans.services.impl;

import com.easypay.cornucopiacommon.bean.EncryptResult;
import com.easypay.cornucopiacommon.bean.HuiFuResp;
import com.easypay.cornucopiacommon.enums.RespCode;
import com.easypay.cornucopiacommon.enums.UserLevel;
import com.easypay.cornucopiacommon.utils.EncryptUtil;
import com.easypay.cornucopiatrans.biz.CheckNameBiz;
import com.easypay.cornucopiatrans.biz.CheckPhotoBiz;
import com.easypay.cornucopiatrans.biz.TransactionalBiz;
import com.easypay.cornucopiatrans.dal.dao.impl.UserInfoMapperImpl;
import com.easypay.cornucopiatrans.dal.dao.impl.UserMapMapperImpl;
import com.easypay.cornucopiatrans.dal.pojo.UserInfo;
import com.easypay.cornucopiatrans.dal.pojo.UserMap;
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

    @Autowired
    private EncryptUtil encryptUtil;

    @Autowired
    private TransactionalBiz transactionalBiz;

    @Autowired
    private UserMapMapperImpl userMapMapper;

    /**
     * 调用外部系统进行实名认证
     * @param idno
     * @param legelName
     * @return
     */
    @Override
    public BaseResponse checkName(String userId,String idno,String legelName ,String userName){
        BaseResponse baseResponse = new BaseResponse();
        UserInfo userInfo = userInfoMapper.selectByUniqueIndex(userId);
        if(userInfo.getUserLevel() > UserLevel.ONE.getUserLevel()){
            log.info("已经进行过实名认证");
            baseResponse.setRespCode(RespCode.CODE_000.getRespCode());
            baseResponse.setRespDesc(RespCode.CODE_000.getRespDesc());
            return baseResponse;
        }

        HuiFuResp huiFuResp = checkNameBiz.ckeck(userId,idno,legelName,userName);
        if(!RespCode.CODE_000.getRespCode().equals(huiFuResp.getRespCode())){
            log.info("调用通道开户失败");
            baseResponse.setRespCode(RespCode.CODE_004.getRespCode());
            baseResponse.setRespDesc(RespCode.CODE_004.getRespDesc());
            return baseResponse;
        }

        EncryptResult encryptResult = encryptUtil.encrypt(idno);

        UserInfo record = new UserInfo();
        record.setId(userInfo.getId());
        record.setIdNo(encryptResult.getCipherText());
        record.setIdNoMask(encryptResult.getMaskText());
        record.setUserLevel(UserLevel.TWO.getUserLevel());
        record.setLegalName(legelName);
        record.setUserName(userName);

        UserMap userMap = userMapMapper.selectByUserId(userId);
        userMap.setMemberId(huiFuResp.getMemberId());
        userMap.setMemberLevel("52");

        transactionalBiz.checkName(record,userMap);

        baseResponse.setRespCode(RespCode.CODE_000.getRespCode());
        baseResponse.setRespDesc(RespCode.CODE_000.getRespDesc());
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
