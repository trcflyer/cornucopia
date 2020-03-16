package com.easypay.cornucopiatrans.biz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.easypay.cornucopiacommon.BaseRequestHuifu;
import com.easypay.cornucopiacommon.bean.HuiFuResp;
import com.easypay.cornucopiacommon.enums.HuiFuUrlEmum;
import com.easypay.cornucopiacommon.utils.Aes128Uitl;
import com.easypay.cornucopiacommon.utils.HttpClientUtils;
import com.easypay.cornucopiacommon.utils.RSAUtil;
import com.easypay.cornucopiatrans.dal.dao.impl.UserMapMapperImpl;
import com.easypay.cornucopiatrans.dal.pojo.UserMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class QueryAccoutInfoBiz extends BaseRequestHuifu {

    @Autowired
    private HttpClientUtils httpClientUtils;

    @Autowired
    private RSAUtil rsaUtil;

    @Autowired
    private Aes128Uitl aes128Uitl;

    @Autowired
    private UserMapMapperImpl userMapMapper;

    public HuiFuResp queryAccoutInfo(String userId){


            initMap();
            UserMap userMap = userMapMapper.selectByUserId(userId);

            dataMap.put("memberId", userMap.getMemberId());
            String jsonData = JSONObject.toJSONString(dataMap);
            Map<String,String> params = new HashMap<>(2);
            params.put("jsonData",jsonData);
            params.put("checkValue", Hex.encodeHexString(rsaUtil.sign(jsonData)));
            JSONObject jsonObject = httpClientUtils.doPost(HuiFuUrlEmum.URL_7303.getRequestUrl(),params);

            HuiFuResp huiFuResp = JSON.parseObject(jsonObject.getString("jsonData"),HuiFuResp.class);
            return huiFuResp;


    }

}
