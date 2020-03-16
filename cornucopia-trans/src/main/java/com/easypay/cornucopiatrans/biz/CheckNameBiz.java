package com.easypay.cornucopiatrans.biz;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.easypay.cornucopiacommon.BaseRequestHuifu;
import com.easypay.cornucopiacommon.bean.EncryptResult;
import com.easypay.cornucopiacommon.bean.HuiFuResp;
import com.easypay.cornucopiacommon.enums.HuiFuUrlEmum;
import com.easypay.cornucopiacommon.utils.Aes128Uitl;
import com.easypay.cornucopiacommon.utils.EncryptUtil;
import com.easypay.cornucopiacommon.utils.HttpClientUtils;
import com.easypay.cornucopiacommon.utils.RSAUtil;
import com.easypay.cornucopiatrans.dal.dao.impl.UserLoginMapperImpl;
import com.easypay.cornucopiatrans.dal.pojo.UserLogin;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class CheckNameBiz extends BaseRequestHuifu {

    @Autowired
    private HttpClientUtils httpClientUtils;

    @Autowired
    private RSAUtil rsaUtil;

    @Autowired
    private Aes128Uitl aes128Uitl;

    @Autowired
    private EncryptUtil encryptUtil;

    @Autowired
    private UserLoginMapperImpl userLoginMapper;

    /**
     * 调用汇付系统进行进件
     */
    public HuiFuResp ckeck(String userId,String inNo,String legalName,String userName){
        initMap();
        UserLogin userLogin = userLoginMapper.selectByUserId(userId);

        EncryptResult encryptResultMobile = encryptUtil.decrypt(userLogin.getLoginId());


        dataMap.put("mobileNo", aes128Uitl.encrypt(encryptResultMobile.getPlainText()));
        dataMap.put("userId",UUID.randomUUID().toString().replaceAll("-", ""));
        dataMap.put("legalName",legalName);
        dataMap.put("idNo",aes128Uitl.encrypt(inNo));
        dataMap.put("certIdType","00");
        dataMap.put("merchName",userName);
        dataMap.put("merchShortName",userName);
       String jsonData = JSONObject.toJSONString(dataMap);
        Map<String,String> params = new HashMap<>(2);
        params.put("jsonData",jsonData);
        params.put("checkValue", Hex.encodeHexString(rsaUtil.sign(jsonData)));
        JSONObject jsonObject = httpClientUtils.doPost(HuiFuUrlEmum.URL_7100.getRequestUrl(),params);

        HuiFuResp huiFuResp = JSON.parseObject(jsonObject.getString("jsonData"),HuiFuResp.class);
        return huiFuResp;
    }

}
