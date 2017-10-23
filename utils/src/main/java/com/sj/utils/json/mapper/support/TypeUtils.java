package com.sj.utils.json.mapper.support;

import java.util.*;

/**
 * Created by yangrd on 2017/6/29.
 */
public final class TypeUtils {

    private TypeUtils() {
    }

    /**
     * --8大基本类型与其包装类
     */

    public static boolean isBool(Class<?> type) {
        return type == Boolean.class || type == Boolean.TYPE;
    }

    public static boolean isByte(Class<?> type) {
        return type == Byte.class || type == Byte.TYPE;
    }

    public static boolean isShort(Class<?> type) {
        return type == Short.class || type == Short.TYPE;
    }

    public static boolean isInt(Class<?> type) {
        return type == Integer.class || type == Integer.TYPE;
    }

    public static boolean isLong(Class<?> type) {
        return type == Long.class || type == Long.TYPE;
    }

    public static boolean isFloat(Class<?> type) {
        return type == Float.class || type == Float.TYPE;
    }

    public static boolean isDouble(Class<?> type) {
        return type == Double.class || type == Double.TYPE;
    }

    public static boolean isChar(Class<?> type) {
        return type == Character.class || type == Character.TYPE;
    }

    /**
     * 容器类
     */

    public static boolean isCollection(Class<?> type) {
        return type == Collection.class || Collection.class.isAssignableFrom(type);
    }

    public static boolean isSet(Class<?> type) {
        return type == Set.class || Set.class.isAssignableFrom(type);
    }

    public static boolean isList(Class<?> type) {
        return type == List.class || List.class.isAssignableFrom(type);
    }

    public static boolean isMap(Class<?> type) {
        return type == Map.class || Map.class.isAssignableFrom(type);
    }

    /**
     * 常用类型
     */

    public static boolean isString(Class<?> type) {
        return type == String.class;
    }

    public static boolean isDate(Class<?> type) {
        return type == Date.class;
    }

    //TODO 暂不支持数组

    public static boolean isSystemType(Class<?> type) {
        return isString(type) || isCollection(type) || isDate(type) || isMap(type) ||
                isInt(type) || isBool(type) || isLong(type) || isFloat(type) || isDouble(type) || isShort(type) || isByte(type) || isChar(type);

    }
}
