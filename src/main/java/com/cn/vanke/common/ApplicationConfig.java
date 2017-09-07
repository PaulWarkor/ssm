package com.cn.vanke.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Properties;

/**
 * 配置文件
 */
public class ApplicationConfig extends PropertyPlaceholderConfigurer {

    private static Properties properties;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties pro) throws BeansException {
        super.processProperties(beanFactoryToProcess, pro);
        properties = pro;
    }

    /**
     * 获取指定配置信息
     * @param name 名称
     * @param defaultValue 默认值
     * @return
     */
    public static String getProperty(String name, String defaultValue) {
        String value = null == properties ? "" : properties.getProperty(name);
        return StringUtils.isEmpty(value) ? defaultValue : value;
    }

    /**
     * 获取指定配置信息
     * @param name 名称
     * @return
     */
    public static String getProperty(String name) {
        String value = null == properties ? "" : properties.getProperty(name);
        return StringUtils.isEmpty(value) ? "" : value;
    }

    public static void setProperty(String name, String value) {
        properties.setProperty(name, value);
    }

}