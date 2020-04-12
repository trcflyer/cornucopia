package com.easypay.cornucopiacommon.utils;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.DES;
import com.easypay.cornucopiacommon.bean.EncryptResult;

/**
 * 加密机实现加解密
 */
public class EncryptUtil {

    private final static String KEY = "02ecfed591a83bce";

    /**
     * 加密
     * @param content
     * @return
     */
    public static EncryptResult encrypt(String content){
        EncryptResult result = new EncryptResult();
        result.setPlainText(content);

        DES des = SecureUtil.des(HexUtil.decodeHex(KEY));
        String encryptHex = des.encryptHex(content);
        result.setCipherText(encryptHex);

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(content.substring(0,6)).append("****").append(content.substring(10));
        result.setMaskText(stringBuffer.toString());

        return result;
    }

    /**
     * 解密
     * @param content
     * @return
     */
    public static EncryptResult decrypt(String content){
        EncryptResult result = new EncryptResult();
        result.setCipherText(content);
        DES des = SecureUtil.des(HexUtil.decodeHex(KEY));
        String decryptStr = des.decryptStr(content);
        result.setPlainText(decryptStr);

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(decryptStr.substring(0,6)).append("****").append(decryptStr.substring(10));
        result.setMaskText(stringBuffer.toString());

        return result;
    }

    /*
    String content = "test中文";

    //随机生成密钥
    byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.DES.getValue()).getEncoded();

    //构建
    DES des = SecureUtil.des(key);

    //加密解密
    byte[] encrypt = des.encrypt(content);
    byte[] decrypt = des.decrypt(encrypt);

    //加密为16进制，解密为原字符串
    String encryptHex = des.encryptHex(content);
    String decryptStr = des.decryptStr(encryptHex);

     */

}
