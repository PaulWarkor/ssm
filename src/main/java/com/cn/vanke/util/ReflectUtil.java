package com.cn.vanke.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

/**
 * 反射工具类
 */
public class ReflectUtil {
    static final Logger logger = LoggerFactory.getLogger(ReflectUtil.class);

    /**
     * 获取属性信息
     *
     * @param object    实例
     * @param fieldName 字段名
     * @return 属性值
     */
    public static Object getFieldValue(Object object, String fieldName) {
        Object value = null;
        try {
            Field field = getFieldByFieldName(object, fieldName);
            if (field != null) {
                boolean access = field.isAccessible();
                field.setAccessible(true);
                value = field.get(object);
                field.setAccessible(access);
            }
        } catch (Exception e) {
            logger.debug("can't get field " + fieldName + " value in class " + object);
        }

        return value;
    }

    /**
     * 根据属性名获取属性
     *
     * @param object    实例
     * @param fieldName 属性名
     * @return 属性信息
     */
    public static Field getFieldByFieldName(Object object, String fieldName) {
        if (object == null || fieldName == null) {
            return null;
        }

        for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                return superClass.getDeclaredField(fieldName);
            } catch (Exception e) {
                logger.debug("can't found field " + fieldName + " in class " + object.getClass());
            }
        }

        return null;
    }

    /**
     * 设置属性值
     *
     * @param object    实例
     * @param fieldName 属性名
     * @param value     值
     * @return 是否成功
     */
    public static boolean setFieldValue(Object object, String fieldName, Object value) {
        boolean result = true;
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            boolean access = field.isAccessible();
            field.setAccessible(true);
            field.set(object, value);
            field.setAccessible(access);
        } catch (Exception e) {
            result = false;
        }

        return result;
    }
}
