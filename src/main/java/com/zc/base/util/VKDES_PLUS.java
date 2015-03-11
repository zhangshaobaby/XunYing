package com.zc.base.util;


import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

/**
 * 3DES加密工具类
 */
public class VKDES_PLUS {
	// 密钥
	public static String ENCRYPT_KEY = "MDUAKOUITRSLLLLRTROKHGFR";
	private final static String secretKey = "MDUAKOUITRSLLLLRTROKHGFR";
	// 向量
	private final static String iv = "12345678";
	// 加解密统一使用的编码方式
	private final static String encoding = "utf-8";
	
	 

	/**
	 * 3DES加密
	 * 
	 * @param plainText 普通文本
	 * @return 
	 * @throws Exception 
	 */
	public static String Encrypt(String plainText)   {
		
		try {
			if (plainText == null) {
				return null;
			}
			Key deskey = null;
			DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
			SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
			deskey = keyfactory.generateSecret(spec);
	
			Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
			IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
			byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
			return Base64.encode(encryptData);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 3DES解密
	 * 
	 * @param encryptText 加密文本
	 * @return	 
	 * @throws Exception
	 */
	public static String Decrypt(String decryptString)  {
		try {
			if (decryptString == null) {
				return null;
			}
			Key deskey = null;
			DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
			SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
			deskey = keyfactory.generateSecret(spec);
			Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
			IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
			byte[] a=Base64.decode(decryptString);
			byte[] decryptData = cipher.doFinal(a);
	
			return new String(decryptData, encoding);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Base64DecodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	public static void main(String[] args)
	{
		String a="测试";
		String af=VKDES_PLUS.Encrypt(a);
		System.out.println(af);
		String aff=VKDES_PLUS.Decrypt(af);
		System.out.println(aff);
	}
}


 
