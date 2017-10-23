package com.sj.utils.json.mapper.support;

import cn.sunxyz.common.base.Pair;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sj.utils.json.mapper.JsonKey;
import com.sj.utils.json.mapper.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 洋葱
 * Created by yangrd on 2017/6/30.
 */
public class Onion {

    public static final Onion INSTANCE = new Onion();

    //TODO 此处代码或许存在bug

    public List<Object> peel(JSON json, Field field) {
        if (field.isAnnotationPresent(JsonKey.class)) {
            List<Pair<Integer, String>> pairs;
            Class<?> cls = field.getType();
            if (TypeUtils.isCollection(cls)) {
                pairs = JSONKeySupport.INSTANCE.getJson(field.getAnnotation(JsonKey.class), FieldUtils.getGenericType(field));
            } else {
                pairs = JSONKeySupport.INSTANCE.getJson(field.getAnnotation(JsonKey.class), cls);
            }
            List<Object> result = new ArrayList<>();
            if (json instanceof JSONArray) {
                peel((JSONArray) json, pairs, 0, result);
            } else if (json instanceof JSONObject) {
                peel((JSONObject) json, pairs, 0, result);
            }
            return result;
        } else {
            throw new RuntimeException();
        }
    }

    private void peel(JSONArray jsonArray, List<Pair<Integer, String>> pairs, int i, List<Object> reslut) {
        int index;
        if (pairs.size() > i) {
            index = pairs.get(i).getFirst();
        } else {
            return;
        }
        switch (index) {
            case JSONKeySupport.ARRAYS_INDEX:

            case JSONKeySupport.OBJ_INDEX:
                for (int i1 = 0; i1 < jsonArray.size(); i1++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i1);
                    if (i1 == jsonArray.size() - 1) {
                        i++;
                    }
                    peel(jsonObject, pairs, i, reslut);
                }
                break;
            case JSONKeySupport.COMMON_INDEX:
                for (int i1 = 0; i1 < jsonArray.size(); i1++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i1);
                    peel(jsonObject, pairs, i, reslut);
                }
                break;
            default:
                JSON json = (JSON) jsonArray.get(index);
                i++;
                if (json instanceof JSONArray) {
                    peel((JSONArray) json, pairs, i, reslut);
                } else if (json instanceof JSONObject) {
                    peel((JSONObject) json, pairs, i, reslut);
                }
                break;
        }
    }

    public void peel(JSONObject jsonObject, List<Pair<Integer, String>> pairs, int i, List<Object> reslut) {
        String key;
        int index;
        if (pairs.size() > i) {
            Pair<Integer, String> pair = pairs.get(i);
            key = pair.getSecond();
            index = pair.getFirst();
        } else {
            return;
        }
        switch (index) {
            case JSONKeySupport.ARRAYS_INDEX:
                JSONArray jsonArray1 = jsonObject.getJSONArray(key);
                if (i == pairs.size() - 1) {
                    reslut.add(jsonArray1);
                }
                peel(jsonArray1, pairs, ++i, reslut);
                break;
            case JSONKeySupport.OBJ_INDEX:
                JSONObject jsonObject1 = jsonObject.getJSONObject(key);
                if (i == pairs.size() - 1) {
                    reslut.add(jsonObject1);
                }
                peel(jsonObject1, pairs, ++i, reslut);
                break;
            case JSONKeySupport.COMMON_INDEX:
                JSONKeySupport.Triad<Integer, String, Class<?>> triad = (JSONKeySupport.Triad<Integer, String, Class<?>>) pairs.get(i);
                if (i == pairs.size() - 1) {
                    reslut.add(ValueSupport.INSTANCE.getValue(jsonObject, key, triad.getThird()));
                }
                break;
            default:
                JSON json = (JSON) jsonObject.getJSONArray(key).get(index);
                i++;
                if (json instanceof JSONArray) {
                    peel((JSONArray) json, pairs, i, reslut);
                } else if (json instanceof JSONObject) {
                    peel((JSONObject) json, pairs, i, reslut);
                }
                break;
        }
    }
}
