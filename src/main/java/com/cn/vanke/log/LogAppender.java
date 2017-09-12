package com.cn.vanke.log;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Priority;

/**
 * 功能说明:日志分级输出
 */
public class LogAppender extends DailyRollingFileAppender {
	
	/**
	 * 只判断是否相等，而不判断优先级,用于日志分级输出
	 * 同时需要在log4j.properties中配置
	 */
	@Override
	public boolean isAsSevereAsThreshold(Priority priority) {
		return super.getThreshold().equals(priority);
	}
}
