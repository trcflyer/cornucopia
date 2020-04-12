package com.easypay.cornucopiacommon.bean;



public class EncryptResult {
    private String plainText;//明文
    private String cipherText;//密文
    private String maskText;//掩码

    public String getPlainText() {
        return plainText;
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }

    public String getCipherText() {
        return cipherText;
    }

    public void setCipherText(String cipherText) {
        this.cipherText = cipherText;
    }

    public String getMaskText() {
        return maskText;
    }

    public void setMaskText(String maskText) {
        this.maskText = maskText;
    }
}
