package com.cn.vanke.config;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

/***
 * properties文件获取工具类
 * 
 */
public class PropertiesConfig {
	/**
	 * 属性工具类
	 */
	private static Properties props;
	private static PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();
	/**
	 * 默认读取文件编码方式
	 */
	private static final String DEFAULT_ENCODING = "UTF-8";
	/**
	 * 全局日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(PropertiesConfig.class);
	/**
	 * 所有properties文件所在父目录
	 */
	private static final String PREFIX_PATH = "properties/";
	/**
	 * 配置多个properties
	 */
	private static final String[] locationPath = new String[] {"detectApi.properties", "detectApi1.properties" };

	static {
		loadProperties(locationPath);
	}

	/***
	 * 全局加载多个properties
	 * 
	 * @param locations
	 */
	public static void loadProperties(String... locations) {
		logger.info("开始加载properties文件内容.......");
		props = new Properties();
		for (String location : locations) {
			InputStream is = null;
			try {
				Resource resource = new ClassPathResource(PREFIX_PATH + location);
				is = resource.getInputStream();
				propertiesPersister.load(props, new InputStreamReader(is, DEFAULT_ENCODING));
			} catch (FileNotFoundException e) {
				logger.error(location + "properties文件未找到");
			} catch (IOException ex) {
				logger.info("properties文件未找到 : " + location + " : " + ex.getMessage());
			} finally {
				try {
					if (null != is) {
						is.close();
					}
				} catch (IOException e) {
					logger.error("jdbc.properties文件流关闭出现异常");
				}
			}
		}
		logger.info("加载properties文件内容完成...........");
	}

	/***
	 * 通过key获取value值
	 * 
	 * @param key
	 * @return
	 */
	public static String getProperty(String key) {
		if (null == props) {
			loadProperties(locationPath);
		}
		return props.getProperty(key);
	}

	/***
	 * 通过key获取value值,如果值不存在则为默认值
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getProperty(String key, String defaultValue) {
		if (null == props) {
			loadProperties(locationPath);
		}
		return props.getProperty(key, defaultValue);
	}

	/***
	 * 写入key和value到properties文件中
	 * 
	 * @param fileName
	 * @param key
	 * @param value
	 */
	public static void writeValueByKey(String fileName,String key,Object value){
		Map<String,Object> properties = new HashMap<String,Object>();
		properties.put(key, value);
		writeValues(fileName,properties);
	}
	
	/***
	 * 写入键值对到properties文件中
	 * @param fileName
	 * @param properties
	 */
	public static void writeValues(String fileName,Map<String, Object> properties) {
		logger.info("写入properties文件 : " + fileName + " 开始...........");
		Properties prop = new Properties();
		InputStream in = null;
		OutputStream out = null;
		try {
			in = PropertiesConfig.class.getClassLoader().getResourceAsStream(PREFIX_PATH + fileName);
			prop.load(in);
			out = new FileOutputStream(PropertiesConfig.class.getResource("/" + PREFIX_PATH + fileName).getPath());
			if (properties != null) {
				Set<String> set = properties.keySet();
				for (String key : set) {
					prop.setProperty(key, properties.get(key).toString());
					logger.info("更新成功==> " + fileName + "的键（" + key + "）值为：" + properties.get(key));
				}
			}
			prop.store(out, "update properties");
		} catch (FileNotFoundException e) {
			logger.error("properties文件未找到 : " + fileName);
		} catch (IOException ex) {
			logger.info("properties写入出现异常 : " + ex.getMessage());
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.flush();
					out.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		logger.info("写入properties文件 : " + fileName + " 成功...........");
	}
}
