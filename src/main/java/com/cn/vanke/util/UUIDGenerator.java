/**
 * 
 */
package com.cn.vanke.util;

import java.util.UUID;

/**
 *  
 * 功能说明：UUID 生成器
 * 
 * UUIDGenerator.java
 */
public class UUIDGenerator {
	
	/**
	 * 生成UUID(JDK自带，去掉中间的'-'字符)
	 * @return
	 */
	public static String generateUUID(){
		String uuid =  generateOriginnalUUID();
		uuid = uuid.replaceAll("-", "");
		return uuid;
	}
	
	/**
	 * 生成UUID(JDK自带，中间带有'-'分割符)
	 * @return
	 */
	public static String generateOriginnalUUID(){
		return UUID.randomUUID().toString();
	}

}
