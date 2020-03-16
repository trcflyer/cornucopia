package com.easypay.cornucopiacommon.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import java.io.File;
import java.io.FileInputStream;
import java.security.*;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <h1>RSA工具类</h1> 
 * 用于RSA SHA256的签名验证签名
 * 和用证书进行加解密
 * 
 * 	 * JAVA生产密钥方法：
	 * keytool -genkey -keyalg RSA -keysize 2048 -validity 36500 -alias ceres_test -
keypass 123456 -keystore d:\ceres.keystore -storepass 123456 -dname "CN=CERES,OU=HF,O=HF,L=SH,ST=SH,C=CN"
       用JAVA Bin目录下的KEYTOOL生成， keyalg 指定算法， keysize指定密钥长度, validity指定有效期 alias指定密钥别名
       keypass指定密钥库密码 keystore指定密钥库地址 storepass指定密钥访问密码 dname指定证书机构信息
       导出公钥方法：
       keytool -export -alias ceres_test -keystore d:\ceres.keystore -file ceres_test_public.cer
       alias指定密钥别名  keystore指定密码库路径 -file 指定生成公钥文件名
       
 签名用法：
 String check = Hex.encodeHexString(RSAUtil.sign(content));
 采用默认的服务器私钥进行签名，将签名结果进行16进制编码
 RSAUtil.verifySign("ceres_test_public", content,
					Hex.decodeHex(check.toCharArray()))
 指定公钥证书进行签名验证	
 加密：
 	String result = Hex.encodeHexString(RSAUtil.encrypt(
				"ceres_test_public", content, "UTF-8"));
解密：
new String(RSAUtil.decrypt("ceres_test",	
					Hex.decodeHex(result.toCharArray())))			
 				
 * @author jiawei.wang
 * @version RSAUtil.java, v1.0, 2018年10月26日 下午5:21:46 jiawei.wang
 */
@Component
public class RSAUtil {

	static final String KEY_ALGORITHM = "RSA";
	static final String CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding";
	static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
	static final String ENCODE_ALGORITHM = "SHA-256";

	/**
	 * 公钥的别名列表，用,号分割，别名必须是公钥文件名的除去扩展名的部分
	 */
	@Value("${rsa.publicKeys}")
	 String PUBLIC_KEYS ;
	/**
	 * 服务器私钥别名
	 */
	@Value("${rsa.serverAlias}")
	 String SERVER_ALIAS ;
	/**
	 * 私钥密钥库密码
	 */
	@Value("${rsa.keyPassword}")
	 String KEY_PASSWORD ;
	/**
	 * 私钥别名密码
	 */
	@Value("${rsa.aliasPassword}")
	 String ALIAS_PASSWORD ;
	/**
	 * 私钥库地址
	 */
	@Value("${rsa.privateKeyPath}")
	 String PRIVATE_KEY_PATH ;
	/**
	 * 公钥文件存放基础路径
	 */
	@Value("${rsa.publicKeyBasePath}")
	  String PUBLIC_KEY_BASE_PATH ;
	/**
	 * 公钥文件扩展名，通常是.cer
	 */
	@Value("${rsa.publicKeyFileType}")
	 String PUBLIC_KEY_FILE_TYPE ;
	static final ConcurrentHashMap<String, Key> KEYMAP = new ConcurrentHashMap<String, Key>(
			10);

	@PostConstruct
	public void init() {
		File privateFile = new File(PRIVATE_KEY_PATH);
		if (privateFile.exists()) {
			PrivateKey privateKey = getKeyFromKeyStore(PRIVATE_KEY_PATH,
					KEY_PASSWORD, SERVER_ALIAS, ALIAS_PASSWORD);
			KEYMAP.put(SERVER_ALIAS, privateKey);
		}
		if (StringUtils.isNotBlank(PUBLIC_KEYS)) {
			String[] publicKeys = PUBLIC_KEYS.split(",");
			for (int i = 0; i < publicKeys.length; i++) {
				String publicKeyName = publicKeys[i];

				String publicFilePath = new StringBuilder(PUBLIC_KEY_BASE_PATH)
						.append(File.separator).append(publicKeyName).append(PUBLIC_KEY_FILE_TYPE)
						.toString();
				File publicKeyFile = new File(publicFilePath);
				if (publicKeyFile.exists()) {
					PublicKey publicKey = getPublicKeyFromCer(publicFilePath);
					KEYMAP.put(publicKeyName, publicKey);
				}
			}
		}
	}

	/**
	 * 签名
	 * 
	 * @param privateKey
	 *            私钥
	 * @param text
	 *            明文
	 * @return
	 */
	public byte[] sign(PrivateKey privateKey, String text) {
		MessageDigest messageDigest;
		byte[] signed = null;
		try {
			messageDigest = MessageDigest.getInstance(ENCODE_ALGORITHM);
			messageDigest.update(text.getBytes());
			byte[] outputDigest = messageDigest.digest();

			Signature Sign = Signature.getInstance(SIGNATURE_ALGORITHM);
			Sign.initSign(privateKey);
			Sign.update(outputDigest);
			signed = Sign.sign();

		} catch (Exception e) {
			throw new RuntimeException("签名失败", e);
		}
		return signed;
	}

	/**
	 * 验签
	 * 
	 * @param publicKey
	 *            公钥
	 * @param text
	 *            明文
	 * @param signed
	 *            签名
	 */
	public boolean verifySign(PublicKey publicKey, String text,
			byte[] signed) {
		MessageDigest messageDigest;
		boolean SignedSuccess = false;
		try {
			messageDigest = MessageDigest.getInstance(ENCODE_ALGORITHM);
			messageDigest.update(text.getBytes());
			byte[] outputDigest = messageDigest.digest();
			Signature verifySign = Signature.getInstance(SIGNATURE_ALGORITHM);
			verifySign.initVerify(publicKey);
			verifySign.update(outputDigest);
			SignedSuccess = verifySign.verify(signed);
		} catch (Exception e) {
			throw new RuntimeException("验证签名异常", e);
		}
		return SignedSuccess;
	}

	/**
	 * 
	 * <h2>加密</h2>
	 * 
	 * @param key 密钥文件
	 * @param character 待加密文本
	 * @param character 编码
	 * @return
	 */
	public byte[] encrypt(Key key, String content, String character) {
		try {
			Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return cipher.doFinal(content.getBytes(character));
		} catch (Exception e) {
			throw new RuntimeException("RSA加密异常", e);
		}
	}

	/**
	 * 
	 * <h2>加密</h2>
	 * @param key 密钥文件别名
	 * @param character 待加密文本
	 * @param character 编码
	 * @return
	 */
	public byte[] encrypt(String key, String content, String character) {
		if (!KEYMAP.containsKey(key)) {
			throw new RuntimeException(String.format("密钥%s不存在", key));
		}
		return encrypt(KEYMAP.get(key), content, character);
	}

	/**
	 * 
	 * <h2>解密</h2>
	 * @param key 密钥别名
	 * @param content 解密文本
	 * @return
	 */
	public  byte[] decrypt(String key, byte[] content){
		if (!KEYMAP.containsKey(key)) {
			throw new RuntimeException(String.format("密钥%s不存在", key));
		}
		return decrypt(KEYMAP.get(key), content);
	}
	/**
	 * 
	 * <h2>解密</h2>
	 * @param key 密钥
	 * @param content 解密文本
	 * @return
	 */
	public byte[] decrypt(Key key, byte[] content) {
		try {
			Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, key);
			return cipher.doFinal(content);
		} catch (Exception e) {
			throw new RuntimeException("RSA解密异常", e);
		}
	}

	/**
	 * 
	 * <h2>从cer公钥证书文件读取公钥</h2>
	 * 
	 * @param cerFilePath
	 * @return
	 */
	public static PublicKey getPublicKeyFromCer(String cerFilePath) {
		try {
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			X509Certificate cert = (X509Certificate) cf
					.generateCertificate(new FileInputStream(cerFilePath));
			PublicKey publicKey = cert.getPublicKey();
			return publicKey;
		} catch (Exception e) {
			throw new RuntimeException("读取公钥失败", e);
		}
	}

	/**
	 * 
	 * <h2>从KEYSTORE获取私钥</h2>
	 * 
	 * @param keyFile
	 * @param keyPassword
	 * @param alias
	 * @param aliesPassword
	 * @return
	 */
	public PrivateKey getKeyFromKeyStore(String keyFile,
			String keyPassword, String alias, String aliesPassword) {
		try {
			FileInputStream fis = new FileInputStream(keyFile);
			KeyStore keyStore = KeyStore.getInstance("JKS");
			keyStore.load(fis, keyPassword.toCharArray());
			fis.close();
			PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias,
					aliesPassword.toCharArray());
			return privateKey;
		} catch (Exception e) {
			throw new RuntimeException("获取私钥失败", e);
		}
	}

	/**
	 * 
	 * <h2>签名</h2>
	 * 
	 * @param content
	 * @return
	 */
	public byte[] sign(String content) {
		if (!KEYMAP.containsKey(SERVER_ALIAS)) {
			throw new RuntimeException(String.format("私钥%s不存在", SERVER_ALIAS));
		}
		return sign((PrivateKey) KEYMAP.get(SERVER_ALIAS), content);
	}

	/**
	 * 
	 * <h2>公钥验证签名</h2>
	 * 
	 * @param publicKey
	 * @param content
	 * @param checkValue
	 * @return
	 */
	public boolean verifySign(String publicKey, String content,
			byte[] checkValue) {
		if (!KEYMAP.containsKey(publicKey)) {
			throw new RuntimeException(String.format("公钥%s不存在", publicKey));
		}
		return verifySign((PublicKey) KEYMAP.get(publicKey), content,
				checkValue);
	}

 

}
