package com.cn.vanke.util;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 功能说明：该功能具提供一个基于系统时间的AtomicLong ID生成实现
 * 
 * IDGenerator.java
 */
public final class IDGenerator {

	private static AtomicLong lastTime = new AtomicLong(
			System.currentTimeMillis());

	/**
	 * 生成一个13位不重复字符串（支持多线程调用）
	 * 
	 * @return String
	 */
	public static String generator() {
		return Long.toString(lastTime.incrementAndGet());
	}

	/**
	 * 生成一个不重复字符串，且带上传入的前缀,如需要分隔符，需要自行处理
	 * @param prefix
	 * @return String
	 */
	public static String generator(String prefix) {
		if (prefix == null)
			prefix = "";
		return prefix + Long.toString(lastTime.incrementAndGet());
	}
	
	
	/**
	 * 生成加前缀和后缀的ID流水号
	 * @param prefix 前缀
	 * @param suffix 后缀
	 * @return
	 */
	public static String generator(String prefix,String suffix){
		if(prefix == null){
			prefix = "";
		}
		if(suffix == null){
			suffix = "";
		}
		String generateId = generator();
		StringBuilder builder = new StringBuilder(prefix.length() 
				+ generateId.length() + suffix.length());
		builder.append(prefix).append(generateId).append(suffix);
		return builder.toString();
	}
	
}
