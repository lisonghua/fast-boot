package com.sj.utils.json.mapper.support;

import cn.sunxyz.common.base.Pair;
import cn.sunxyz.common.base.annotation.Nullable;
import cn.sunxyz.common.utils.StringUtil;
import com.sj.utils.json.mapper.JsonKey;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by yangrd on 2017/6/30.
 */
public class JSONKeySupport {

    public static final int ARRAYS_INDEX = -1;
    public static final int OBJ_INDEX = -2;
    public static final int COMMON_INDEX = -3;

    public static final JSONKeySupport INSTANCE = new JSONKeySupport();

    // Pair first :-1 为集合 -2 为普通对象 -3为基本类型 大于-1为数组拿取下标
    // Pair second : key

    public List<Pair<Integer, String>> getJson(JsonKey jsonKey, Class<?> type) {
        List<Pair<Integer, String>> pairList = getJson(jsonKey);
        if (TypeUtils.isSystemType(type)) {
            Pair<Integer, String> lastPair = pairList.get(pairList.size() - 1);
            pairList.remove(lastPair);
            pairList.add(Triad.of(COMMON_INDEX, lastPair.getSecond(), type));
        }
        return pairList;
    }

    public List<Pair<Integer, String>> getJson(JsonKey jsonKey) {
        String fieldNames = jsonKey.value();
        if (StringUtil.isNotEmpty(fieldNames)) {
            if (fieldNames.indexOf(".") > 0) {
                String[] names = fieldNames.split("\\.");
                return Arrays.asList(names).stream().map(this::getPair).collect(Collectors.toList());
            } else {
                return Arrays.asList(getPair(fieldNames));
            }
        } else {
            throw new RuntimeException("JsonKey is not null");
        }
    }

    private Pair<Integer, String> getPair(String name) {
        Objects.requireNonNull(name);
        int arrayStart = name.indexOf("[");
        if (arrayStart > 0) {
            int arrayEnd = name.indexOf("]");
            if (arrayEnd > 0) {
                String key = name.substring(0, arrayStart);
                String arrayIndex = name.substring(arrayStart + 1, arrayEnd);
                if (StringUtil.isNotEmpty(arrayIndex)) {
                    int index = Integer.valueOf(arrayIndex);
                    if (index < 0) {
                        throw new IndexOutOfBoundsException(key + "[" + index + "]");
                    }
                    return Pair.of(index, key);
                } else {
                    return Pair.of(ARRAYS_INDEX, key);
                }
            } else {
                throw new IllegalArgumentException("Array definition error");
            }
        } else {
            return Pair.of(OBJ_INDEX, name);
        }
    }

    public static class Triad<A, B, C> extends Pair<A, B> {
        private final C third;

        public Triad(@Nullable A first, @Nullable B second, @Nullable C third) {
            super(first, second);
            this.third = third;
        }

        public C getThird() {
            return third;
        }

        public static <A, B, C> Triad<A, B, C> of(@Nullable A first, @Nullable B second, @Nullable C third) {
            return new Triad(first, second, third);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            Triad<?, ?, ?> triad = (Triad<?, ?, ?>) o;

            return third != null ? third.equals(triad.third) : triad.third == null;
        }

        @Override
        public int hashCode() {
            int result = super.hashCode();
            result = 31 * result + (third != null ? third.hashCode() : 0);
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(new JSONKeySupport().getPair("name[12]").getFirst());
    }
}
