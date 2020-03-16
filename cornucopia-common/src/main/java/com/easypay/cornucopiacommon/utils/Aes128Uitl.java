package com.easypay.cornucopiacommon.utils;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.crypto.cipher.CryptoCipher;
import org.apache.commons.crypto.cipher.CryptoCipherFactory;
import org.apache.commons.crypto.cipher.CryptoCipherFactory.CipherProvider;
import org.apache.commons.crypto.utils.Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;
import java.util.Properties;

@Slf4j
@Service
public class Aes128Uitl {

    private static final  String transform = "AES/CBC/PKCS5Padding";
    private static final String CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding";

    @Value("${aes.key}")
    String KEY ;
    @Value("${aes.iv}")
    String IV ;



    /**
     *
     * <h2>加密报文</h2>
     *
     * @param input
     *            报文字节数组
     * @param keyByte
     *            密钥字节数组
     * @param ivByte
     *            iv字节数组
     * @return
     */
    private static byte[] encrypt(byte[] input, byte[] keyByte, byte[] ivByte) {
        final SecretKeySpec key = new SecretKeySpec(keyByte, "AES");
        final IvParameterSpec iv = new IvParameterSpec(ivByte);
        Properties properties = new Properties();
        properties.setProperty(CryptoCipherFactory.CLASSES_KEY,
                CipherProvider.JCE.getClassName());
        // Creates a CryptoCipher instance with the transformation and
        // properties.

        try {
            CryptoCipher encipher = Utils.getCipherInstance(transform,
                    properties);

            byte[] output = new byte[input.length + 16];

            // Initializes the cipher with ENCRYPT_MODE, key and iv.
            encipher.init(Cipher.ENCRYPT_MODE, key, iv);
            // Continues a multiple-part encryption/decryption operation for
            // byte array.

            int updateBytes = encipher
                    .update(input, 0, input.length, output, 0);
            // We must call doFinal at the end of encryption/decryption.
            int finalBytes = encipher.doFinal(input, 0, 0, output, updateBytes);
            // Closes the cipher.
            encipher.close();
            return Arrays.copyOf(output, updateBytes + finalBytes);
        } catch (Exception e) {
            throw new RuntimeException("加密失败", e);
        }

    }

    /**
     *
     * <h2>解密字节数组</h2>
     *
     * @param input
     *            输入加密信息字节数组
     * @param keyByte
     *            密钥字节数组
     * @param ivByte
     *            IV字节数组
     * @return 解密字节数组
     */
    private static byte[] decrypt(byte[] input, byte[] keyByte, byte[] ivByte) {
        final SecretKeySpec key = new SecretKeySpec(keyByte, "AES");
        final IvParameterSpec iv = new IvParameterSpec(ivByte);
        Properties properties = new Properties();
        properties.setProperty(CryptoCipherFactory.CLASSES_KEY,
                CipherProvider.JCE.getClassName());
        try {
            CryptoCipher decipher = Utils.getCipherInstance(transform,
                    properties);
            decipher.init(Cipher.DECRYPT_MODE, key, iv);
            byte[] decoded = new byte[input.length];
            int finalBytes = decipher.doFinal(input, 0, input.length, decoded,
                    0);
            return Arrays.copyOf(decoded, finalBytes);
        } catch (Exception e) {
            throw new RuntimeException("解密失败", e);
        }
    }

    public String encrypt(String content) {
        try {
            byte[] out = encrypt(content.getBytes("utf-8"),
                    Hex.decodeHex(KEY.toCharArray()),
                    Hex.decodeHex(IV.toCharArray()));
            return Hex.encodeHexString(out);
        }catch (Exception e){
            return  null;
        }

    }
    public String decrypt(String content){
        try {
            byte[] src = decrypt(Hex.decodeHex(content.toCharArray()),
                    Hex.decodeHex(KEY.toCharArray()),
                    Hex.decodeHex(IV.toCharArray()));
            return new String(src,"utf-8");
        }catch (Exception e){
            return  null;
        }
    }
}
