/*******************************************************************************
 * Project   : portal-common
 * Class Name: com.yyq.cloud.portal.common.service.EncryptionUtils
 * Created By: Jonathan 
 * Created on: 2014-6-11 下午4:01:24
 * Copyright © 2008-2014 NATIE All rights reserved.
 ******************************************************************************/
package com.chaojizuojia.carmall.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;





/**
 * <P>加解密工具类</P>
 * @author Jonathan
 */
public class EncryptionUtils {

	public static final String KEY_ALGORITHM_AES = "AES";
	public static final String KEY_ALGORITHM_RSA = "RSA";
	public final static String KEY_ALGORITHM_3DES = "DESede";
	public final static String KEY_ALGORITHM_DES = "DES";
	public static final String ALGORITHM_DES_CBC_PKCS5PADDING = "DES/CBC/PKCS5Padding";
	public static final String ALGORITHM_DES_ECB_PKCS5PADDING = "DES/ECB/PKCS5Padding";
	public static final String ALGORITHM_3DES_ECB_PKCS5PADDING = "DESede/ECB/PKCS5Padding";
	public static final String ALGORITHM_AES_CBC_PKCS7PADDING = "AES/CBC/PKCS7Padding";
	public final static String SIGNATURE_ALGORITHM_SHA1_RSA = "SHA1withRSA";
	public final static String SIGNATURE_ALGORITHM_MD5_RSA = "MD5withRSA";

	static {
		try {
			Security.addProvider(new BouncyCastleProvider());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	private EncryptionUtils() {
	}

	
	/**
	 * <p>采用AES算法加密数据</p>
	 * @param data
	 * @param key
	 * @param ivStr
	 * @return
	 * @throws PortalAppCheckedException
	 * @author Jonathan
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchProviderException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 */

	public static byte[] encryptByAES(byte[] data, byte[] key, byte[] iv) {

          try{
			SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM_AES);
			IvParameterSpec ivps = new IvParameterSpec(iv);
			Cipher cipher = Cipher.getInstance(ALGORITHM_AES_CBC_PKCS7PADDING, "BC");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivps);
			return cipher.doFinal(data);
          } catch (Exception e) {
  			e.printStackTrace();
  			throw new RuntimeException(e.getMessage());
  		}
          
		
	}

	/**
	 * <p>采用AES算法解密数据</p>
	 * @param data
	 * @param key
	 * @param ivStr
	 * @return
	 * @throws PortalAppCheckedException
	 * @author Jonathan
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchProviderException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 */
	public static byte[] decryptByAES(byte[] data, byte[] key, byte[] iv) {
		try{
			SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM_AES);
			IvParameterSpec ivps = new IvParameterSpec(iv);
			Cipher cipher = Cipher.getInstance(ALGORITHM_AES_CBC_PKCS7PADDING, "BC");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivps);
			return cipher.doFinal(data);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	
	public static byte[] initKey(String algorithm, int length) throws NoSuchAlgorithmException  {

			KeyGenerator kg = KeyGenerator.getInstance(algorithm);
			kg.init(length);
			SecretKey secretKey = kg.generateKey();
			return secretKey.getEncoded();
		
	}

}
