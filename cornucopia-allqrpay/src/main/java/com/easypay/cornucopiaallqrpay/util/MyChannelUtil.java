package com.easypay.cornucopiaallqrpay.util;

import com.easypay.cornucopiacommon.constant.PayConstant;

public class MyChannelUtil {

    public static String getChannelName(String channelId){
        String name = null;
        switch (channelId) {
            case PayConstant.PAY_CHANNEL_WX_APP :
                name = "微信APP支付";
                break;
            case PayConstant.PAY_CHANNEL_WX_JSAPI :
               name = "微信扫码支付";
                break;
            case PayConstant.PAY_CHANNEL_WX_NATIVE :
                name = "微信原生扫码支付";
                break;
            case PayConstant.PAY_CHANNEL_WX_MWEB :
                name = "微信H5支付";
                break;
            case PayConstant.PAY_CHANNEL_WX_BAR_CODE :
                name = "微信条码支付";
                break;
            case PayConstant.PAY_CHANNEL_ALIPAY_MOBILE :
                name = "支付宝移动支付";
                break;
            case PayConstant.PAY_CHANNEL_ALIPAY_PC :
                name = "支付宝PC支付";
                break;
            case PayConstant.PAY_CHANNEL_ALIPAY_WAP :
                name = "支付宝扫码支付";
                break;
            case PayConstant.PAY_CHANNEL_ALIPAY_QR :
                name = "支付宝当面付扫码支付";
                break;
            case PayConstant.PAY_CHANNEL_ALIPAY_BAR_CODE:
                name = "支付宝当面付条码支付";
                break;
            default:
                name = "其他支付";
        }
        return name;
    }

    public static String getSceneName(String scene){
        String name = null;
        switch (scene) {
            case "bar_code":
                name = "支付宝条码支付";
                break;
            case "security_code" :
                name = "支付宝刷脸支付";
                break;
            case "bar_code_w":
                name = "微信条码支付";
                break;
            default:
                name = "其他支付";
        }
        return name;
    }
}
