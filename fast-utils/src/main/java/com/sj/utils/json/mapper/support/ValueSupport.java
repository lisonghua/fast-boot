package com.sj.utils.json.mapper.support;

import cn.sunxyz.common.utils.reflect.ReflectionUtil;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import static com.sj.utils.json.mapper.support.TypeUtils.*;

/**
 * Created by yangrd on 2017/6/30.
 */
public class ValueSupport {

    public static final ValueSupport INSTANCE = new ValueSupport();

    public <T> T getValue(JSONObject jsonObject, String key, Class<T> type) {
        if (isCollection(type)) {
            return getCollectionTypeInstance(type);//容器类型
        } else {
            Object value = getBasicTypeInstance(jsonObject, key, type);//基本类型
            if (value == null) {
                // date String 类型;
                value = getCommonTypeInstance(jsonObject, key, type);//常用类型
                if (value == null) {
                    value = ReflectionUtil.invokeConstructor(type);//用户自定义类型
                }
            }
            return (T) value;
        }
    }

    /**
     * 基本类型
     */
    protected <T> T getBasicTypeInstance(JSONObject jsonObject, String key, Class<T> type) {
        Object value = null;
        if (isString(type)) {
            value = jsonObject.getString(key);
        } else if (isInt(type)) {
            value = jsonObject.getIntValue(key);
        } else if (isLong(type)) {
            value = jsonObject.getLongValue(key);
        } else if (isDouble(type)) {
            value = jsonObject.getDoubleValue(key);
        } else if (isDate(type)) {
            value = jsonObject.getDate(key);
        } else if (isBool(type)) {
            value = jsonObject.getBooleanValue(key);
        } else if (isFloat(type)) {
            value = jsonObject.getFloatValue(key);
        } else if (isShort(type)) {
            value = jsonObject.getShortValue(key);
        } else if (isByte(type)) {
            value = jsonObject.getByteValue(key);
        }
        return (T) value;
    }

    /**
     * 容器类型
     */
    public <T> T getCollectionTypeInstance(Class<T> type) {
        if (isList(type)) {
            return (T) new ArrayList<>();
        } else if (isSet(type)) {
            return (T) new HashSet<>();
        }
        return (T) new ArrayList<>();
    }

    /**
     * 常用类型
     */
    protected <T> T getCommonTypeInstance(JSONObject jsonObject, String key, Class<T> type) {
        Object value = null;
        if (isString(type)) {
            value = jsonObject.getString(key);
        } else if (isDate(type)) {
            value = jsonObject.getDate(key);
        }
        return (T) value;
    }
}
