package com.cn.vanke.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cn.vanke.exception.BasicAppException;

/**
 * 
 * 功能说明：集合工具类
 * 
 * CollectionUtils.java
 */
public class CollectionUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(CollectionUtils.class); 
	
	/**
	 * 判断指定的集合是否为空。
	 * @param collection 待判断的集合
	 * @return 返回指定的集合是否为空。
	 */
	public static Boolean isEmpty(Collection<?> collection) {
		return (collection == null || collection.isEmpty());
	}

	/**
	 * 判断指定的集合是否不为空。
	 * @param collection 待判断的集合
	 * @return 返回指定的集合是否不为空。
	 */
	public static Boolean isNotEmpty(Collection<?> collection) {
		return !isEmpty(collection);
	}

	/**
	 * 判断指定的数组是否为空。
	 * @param array 待判断的数组
	 * @return 返回指定的数组是否为空。
	 */
	public static Boolean isEmpty(Object[] array) {
		return (array == null || array.length == 0);
	}

	/**
	 * 判断指定的数组是否不为空。
	 * @param array 待判断的数组
	 * @return 返回指定的数组是否不为空。
	 */
	public static Boolean isNotEmpty(Object[] array) {
		return !isEmpty(array);
	}

	/**
	 * 判断指定的Map是否为空。
	 * @param map 待判断的Map
	 * @return 返回指定的Map是否为空。
	 */
	public static Boolean isEmpty(Map<?, ?> map) {
		return (map == null || map.isEmpty());
	}

	/**
	 * 判断指定的Map是否不为空。
	 * @param map 待判断的Map
	 * @return 返回指定的Map是否不为空。
	 */
	public static Boolean isNotEmpty(Map<?, ?> map) {
		return !isEmpty(map);
	}

	/**
	 * 移除List中重复的元素。
	 * <br>说明：对象必须显示实现hashCode()和equals()方法
	 * @param <T> 元素类型
	 * @param list 列表
	 */
	public static <T> void removeDuplicate(List<T> list) {
		if(CollectionUtils.isEmpty(list)){
			logger.error("准备移除元素的列表List为空..");
			throw new BasicAppException("准备移除元素的列表List不能为空.");
		}
		HashSet<T> set = new HashSet<T>(list);
		list.clear();
		list.addAll(set);
	}

	/**
	 * 判断数组中是否包含指定元素。
	 * @param <T> 数组类型
	 * @param elements 数组
	 * @param elementToFind 待查找的元素
	 * @return 如果数组中包含指定元素返回true，否则返回false。
	 */
	public static <T> Boolean contains(T[] elements, T elementToFind) {
		if(CollectionUtils.isEmpty(elements)){
			logger.error("检测包含的数组不能为空..");
			throw new BasicAppException("检测包含的数组为空.");
		}
		List<T> elementsList = Arrays.asList(elements);
		return elementsList.contains(elementToFind);
	}

	/**
	 * 复制集合。
	 * @param <T> 集合元素类型
	 * @param source 源集合
	 * @param target 目标集合
	 */
	public static <T> void copy(Collection<T> source, Collection<T> target) {
		if(CollectionUtils.isEmpty(source)){
			logger.error("源集合不能为空..");
			throw new BasicAppException("源集合为空.");
		}
		if(CollectionUtils.isEmpty(target)){
			logger.error("目标集合不能为空..");
			throw new BasicAppException("目标集合为空.");
		}
		//不清除目标集合原有数据
		//target.clear();
		for(T o : source){
			target.add(o);
		}
	}

	/**
	 * 根据键对Map进行排序。
	 * @param <K> 键类型
	 * @param <V> 值类型
	 * @param map Map
	 * @param asc 是否升序
	 * @return 返回排序后的Map。
	 */
	public static <K, V> Map<K, V> sortMapByKey(Map<K, V> map, final Boolean asc) {
		if(CollectionUtils.isEmpty(map)){
			logger.error("Map对象为空..");
			throw new BasicAppException("Map对象不能为空.");
		}
		List<Entry<K, V>> entries = new LinkedList<Entry<K, V>>(map.entrySet());
		Collections.sort(entries, new Comparator<Entry<K, V>>() {
			@SuppressWarnings("unchecked")
			public int compare(Entry<K, V> o1, Entry<K, V> o2) {
				if (asc) {
					return ((Comparable<K>) o1.getKey()).compareTo(o2.getKey());
				} else {
					return -((Comparable<K>) o1.getKey()).compareTo(o2.getKey());
				}
			}
		});

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Entry<K, V> entry : entries) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

	/**
	 * 根据值对Map进行排序。
	 * @param <K>  键类型
	 * @param <V> 值类型
	 * @param map Map
	 * @param asc 是否升序
	 * @return 返回排序后的Map。
	 */
	public static <K, V> Map<K, V> sortMapByValue(Map<K, V> map,
			final Boolean asc) {
		if(CollectionUtils.isEmpty(map)){
			logger.error("Map对象为空..");
			throw new BasicAppException("Map对象不能为空.");
		}
		List<Entry<K, V>> entries = new LinkedList<Entry<K, V>>(map.entrySet());
		Collections.sort(entries, new Comparator<Entry<K, V>>() {
			@SuppressWarnings("unchecked")
			public int compare(Entry<K, V> o1, Entry<K, V> o2) {
				if (asc) {
					return ((Comparable<V>) o1.getValue()).compareTo(o2
							.getValue());
				} else {
					return -((Comparable<V>) o1.getValue()).compareTo(o2
							.getValue());
				}
			}
		});

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Entry<K, V> entry : entries) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}
}
