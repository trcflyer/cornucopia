package com.easypay.cornucopiaallqrpay.service;

import java.util.Map;

/**
 * @author: dingzhiwei
 * @date: 17/9/9
 * @description:
 */
public interface IPayChannel4WxService {

    Map doWxPayReq(String jsonParam);

    Map doWxPayBarCodeReq(String jsonParam);


}
