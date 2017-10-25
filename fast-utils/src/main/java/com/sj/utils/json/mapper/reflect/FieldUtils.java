package com.sj.utils.json.mapper.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by yangrd on 2017/6/30.
 */
public class FieldUtils {
    /**
     * 获取字段的泛型
     */
    public static Class<?> getGenericType(Field field) {
        Type fc = field.getGenericType(); // 关键的地方，如果是List类型，得到其Generic的类型
        if (fc == null) throw new RuntimeException();
        if (fc instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) fc;
            return (Class) pt.getActualTypeArguments()[0];
        }
        throw new RuntimeException();
    }
}
