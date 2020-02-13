package com.easypay.cornucopiatrans.controller;

import com.alibaba.fastjson.JSONObject;
import com.easypay.cornucopiacommon.result.CommonResult;
import com.easypay.cornucopiatrans.dal.dao.impl.TransOrdInfoMapperImpl;
import com.easypay.cornucopiatrans.dal.pojo.TransOrdInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController extends BaseController{



    @Autowired
    private TransOrdInfoMapperImpl transOrdInfoMapper;

    @RequestMapping("test")
    public CommonResult test(){

        TransOrdInfo transOrdInfo1 = transOrdInfoMapper.selectByPrimaryKey(1l);

        TransOrdInfo transOrdInfo2 = transOrdInfoMapper.selectByUniqueIndex(transOrdInfo1.getTransDate(),transOrdInfo1.getSqeId());

        TransOrdInfo transOrdInfo3 = transOrdInfoMapper.selectByOrderId(transOrdInfo1.getOrderId());


        log.info(JSONObject.toJSONString(transOrdInfo1));
        log.info(JSONObject.toJSONString(transOrdInfo2));
        log.info(JSONObject.toJSONString(transOrdInfo3));
        return CommonResult.success(JSONObject.toJSONString(transOrdInfo1));
    }
}
