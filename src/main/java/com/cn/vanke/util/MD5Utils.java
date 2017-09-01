/**
 * 
 */
package com.cn.vanke.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cn.vanke.exception.BasicAppException;

/**
 * 
 * 功能说明： MD5加密工具类
 * 
 * MD5Utils.java
 */
public class MD5Utils {
	 
    private static final Logger logger = LoggerFactory.getLogger(MD5Utils.class); 
	
	private static final String MD5 = "MD5";
	
	/**
	 * MD5 32位加密
	 * @param password 准备加密的密码
	 * @return MD5加密后的密码(转换成小写)
	 */
	public static String encoder(String password){
		try {
		if(password == null){
				logger.error("准备MD5加密的密码不能为空..");
				throw new BasicAppException("准备MD5加密的密码为空.");
		 }
		 MessageDigest digest = MessageDigest.getInstance(MD5);
		 byte[] results = digest.digest(password.getBytes());
		 StringBuilder sb = new StringBuilder();
		 for(byte b:results){
			 int number = b&0xff;
			 String hex = Integer.toHexString(number);
			 if(hex.length() == 1){
				 sb.append("0");
			 }
			 sb.append(hex);
		 }
		 return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			logger.error("MD5数据加密失败。");
			throw new BasicAppException("MD5数据加密失败。", e);
		}
		
	}

}
