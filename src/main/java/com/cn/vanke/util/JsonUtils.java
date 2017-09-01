/**
 * 
 */
package com.cn.vanke.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * 功能说明：JSON转换工具类
 * 
 * JsonUtils.java
 */
public class JsonUtils {

	private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class); 
		
	
	//单例
	private static ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * 对象转换成JSON
	 * @param obj 对象(包括实体对象,Map<String,Object>和List等)
	 * @return
	 */
	public static String object2Json(Object obj){
		String json = null;
		try {
			json = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			logger.error("对象转换JSON失败...");
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * json格式数据转换成对象(对象包括Bean对象，Map和List等)
	 * @param json json格式的字符串
	 * @param clazz 转换类
	 * @return
	 */
	public static Object json2Object(String json,Class<? extends Object> clazz){
		Object object = null;
		try {
			object = mapper.readValue(json, clazz);
		} catch (JsonParseException e) {
			logger.error("JsonParseException:JSON转换成对象失败...");
			e.printStackTrace();
		} catch (JsonMappingException e) {
			logger.error("JsonMappingException:JSON转换成对象失败...");
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("IOException:JSON转换成对象失败...");
			e.printStackTrace();
		}
		return object;
	}
	
	
}
