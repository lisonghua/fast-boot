package com.sj.utils.json.mapper;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * Created by yangrd on 2017/6/30.
 */
public interface IJSONMapper {

    <T> List<T> mapper(JSONArray jsonArray, Class<T> cls);

    <T> T mapper(JSONObject jsonObject, Class<T> cls);

}
