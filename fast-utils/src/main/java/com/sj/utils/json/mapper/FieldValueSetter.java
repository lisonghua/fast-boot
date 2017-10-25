package com.sj.utils.json.mapper;


import cn.sunxyz.common.utils.reflect.ClassUtil;
import cn.sunxyz.common.utils.reflect.ReflectionUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sj.utils.json.mapper.reflect.FieldUtils;
import com.sj.utils.json.mapper.support.Onion;
import com.sj.utils.json.mapper.support.TypeUtils;
import com.sj.utils.json.mapper.support.ValueSupport;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;


/**
 * Created by yangrd on 2017/6/30.
 */
public class FieldValueSetter extends ValueSupport {

    private IJSONMapper jsonMapper;

    public FieldValueSetter(IJSONMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    public void setFieldValue(JSONObject jsonObject, Object obj, Field field) {
        Class<?> type = field.getType();
        String name = field.getName();
        ClassUtil.makeAccessible(field);
        if (field.isAnnotationPresent(JsonKey.class)) {//TODO json-key
            List<Object> jsonList = Onion.INSTANCE.peel(jsonObject, field);
            boolean flag = false;
            for (Object o : jsonList) {
                if (o instanceof JSONArray) {
                    setFieldValue((JSONArray) o, obj, field);
                } else if (o instanceof JSONObject) {
                    setFieldValue((JSONObject) o, obj, field);
                } else if (TypeUtils.isSystemType(field.getType())) {
                    flag = true;
                    break;
                } else {
                    //优化
                }
            }
            if (flag) {
                ReflectionUtil.setField(obj, field, jsonList);
            }
        } else {

            //如果是集合 将 集合拆开  如果是对象直接设值;
            Object value = getValue(jsonObject, name, type);
            ReflectionUtil.setField(obj, field, value);//字段设值
        }
    }

    public void setFieldValue(JSONArray jsonArray, Object obj, Field field) {
        Class<?> type = field.getClass();
        ClassUtil.makeAccessible(field);
        Collection fieldValue = (Collection) getCollectionTypeInstance(type);
        ReflectionUtil.setField(obj, field, fieldValue);//字段设值
        int len = jsonArray.size();
        Class<?> genericType = FieldUtils.getGenericType(field);//获取泛型类型
        for (int i = 0; i < len; i++) {
            if (TypeUtils.isCollection(genericType)) {
                fieldValue.add(jsonMapper.mapper(jsonArray.getJSONArray(i), genericType));
            } else {
                fieldValue.add(jsonMapper.mapper(jsonArray.getJSONObject(i), genericType));
            }
        }
    }


}
