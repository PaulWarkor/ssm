/**
 * 
 */
package com.cn.vanke.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cn.vanke.constants.Encoding;
import com.cn.vanke.exception.BasicAppException;

/**
 * 
 * 功能说明： Base64编解码工具类(基于Apache commons-codec包)
 * 
 * Base64Utils.java
 */
public class Base64Utils {
	
   private static final Logger logger = LoggerFactory.getLogger(Base64Utils.class); 
	
	
  /**
   * Base64字节编码
   * @param binaryData 待编码内容字节数组
   * @return
   */
  public static byte[] encode(byte[] binaryData){
		if(binaryData == null){
			logger.error("准备Base64编码的字节数组为空..");
			throw new BasicAppException("准备Base64编码的字节数组不能为空.");
		}
		return Base64.encodeBase64(binaryData);
	}
	
	/**
	 * Base64编码
	 * @param body 待编码的字符串
	 * @param charsetName 编码格式(默认UTF-8)
	 * @return
	 */
	public static String encode(String body,String charsetName){
		if(body == null){
			logger.error("准备Base64编码的字符串为空..");
			throw new BasicAppException("准备Base64编码的字符串不能为空.");
		}
		//未设置编码，默认UTF-8
		if(StringUtils.isEmpty(charsetName)){
			charsetName = Encoding.UTF_8;
		}
		try{
			return new String(encode(body.getBytes(charsetName)),charsetName);
		} catch (UnsupportedEncodingException e) {
			logger.error("Base64编码时发生异常，不支持字符编码：{}",charsetName);
			//e.printStackTrace();
			throw new BasicAppException("Base64编码时发生异常。", e);
		}
	}
	
	
	/**
	 * Base64编码(默认UTF-8编码)
	 * @param body 待编码的字符串
	 * @return
	 */
	public static String encode(String body){
		
		return encode(body,Encoding.UTF_8);
	}
	
	
	/**
	 * Base64字节数组解码
	 * @param binaryData 待解码内容字节数组
	 * @return
	 */
	public static byte[] decode(byte[] binaryData) {
		if(binaryData == null){
			logger.error("准备Base64解码的字节数组为空..");
			throw new BasicAppException("准备Base64解码的字节数组不能为空.");
		}
		return Base64.decodeBase64(binaryData);
	}
	
	
	/**
	 * Base64字符串解码
	 * @param body 待解码的字符串
	 * @param charsetName 编码格式(默认UTF-8)
	 * @return
	 */
	public static String decode(String body,String charsetName){
		if(body == null){
			logger.error("准备Base64解码的字符串为空..");
			throw new BasicAppException("准备Base64解码的字符串不能为空.");
		}
		//编码格式为空则默认UTF-8编码
		if(StringUtils.isEmpty(charsetName)){
			charsetName = Encoding.UTF_8;
		}
		try{
			return new String(decode(body.getBytes(charsetName)),charsetName);
		} catch (UnsupportedEncodingException e) {
			logger.error("Base64解码时发生异常，不支持字符编码：{}",charsetName);
			//e.printStackTrace();
			throw new BasicAppException("Base64解码时发生异常。", e);
		}
	}
	

	/**
	 * Base64字符串解码(默认编码格式：UTF-8)
	 * @param body64Str 待解码的字符串
	 * @return
	 */
	public static String decode(String body64Str){
		return decode(body64Str,Encoding.UTF_8);
		
	}
	
}
