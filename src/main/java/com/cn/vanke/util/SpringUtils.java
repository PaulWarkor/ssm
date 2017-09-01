package com.cn.vanke.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


/**
 * 功能说明：Spring 工具类
 * 
 * SpringUtils.java
 */
@Component
@Lazy(value = false)
public class SpringUtils implements ApplicationContextAware {
	
	private static ApplicationContext context;
	
	@Override
	public void setApplicationContext(ApplicationContext context) {
		SpringUtils.context = context;
	}

	/**
	 * 获取Spring容器的应用上下文。
	 * @return 返回Spring容器的应用上下文。
	 */
	public static ApplicationContext getContext() {
		return context;
	}

	/**
	 * 从Spring容器中获取指定名称的Bean。
	 * 
	 * @param <T> Bean类型
	 * @param beanName  bean名称
	 * @return 返回指定名称的Bean。
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName) {
		return (T) context.getBean(beanName);
	}


}
