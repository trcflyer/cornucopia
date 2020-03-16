package com.easypay.cornucopiacommon.bean;

import lombok.Data;

@Data
public class EncryptResult {
    private String plainText;//明文
    private String cipherText;//密文
    private String maskText;//掩码
}
