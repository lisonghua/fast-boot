package com.sj.utils.json.mapper;

import cn.sunxyz.common.utils.reflect.ReflectionUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangrd on 2017/6/30.
 */
public class JSONMapper implements IJSONMapper {

    private FieldValueSetter fieldValueSetter = new FieldValueSetter(this);

    @Override
    public <T> List<T> mapper(JSONArray jsonArray, Class<T> cls) {
        List<T> list = new ArrayList();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);//TODO 此处不支持 容器类嵌套
            list.add(mapper(jsonObject, cls));
        }
        return list;
    }

    @Override
    public <T> T mapper(JSONObject jsonObject, Class<T> cls) {
        Field[] fields = FieldUtils.getAllFields(cls);
        Object object = ReflectionUtil.invokeConstructor(cls);
        for (Field field : fields) {
            fieldValueSetter.setFieldValue(jsonObject, object, field);
        }
        return (T) object;
    }
}
